
package vip.xiaonuo.auth.modular.login.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Component;
import vip.xiaonuo.auth.api.SaBaseLoginUserApi;
import vip.xiaonuo.auth.core.enums.SaClientTypeEnum;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.dev.api.DevLogApi;

import javax.annotation.Resource;

/**
 * 自定义登录监听器
 *
 * @author gtc
 *
 **/
@Component
public class AuthListener implements SaTokenListener {

    @Resource(name = "loginUserApi")
    private SaBaseLoginUserApi loginUserApi;


    @Resource
    private DevLogApi devLogApi;

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel)  {
        // 更新用户的登录时间和登录ip等信息
        if(SaClientTypeEnum.B.getValue().equals(loginType)) {
            loginUserApi.updateUserLoginInfo(Convert.toStr(loginId), loginModel.getDevice());
            // 记录B端登录日志
            Object name = loginModel.getExtra("name");
            if(ObjectUtil.isNotEmpty(name)) {
                devLogApi.executeLoginLog(Convert.toStr(name));
            } else {
                devLogApi.executeLoginLog(null);
            }
        } else {
//            clientLoginUserApi.updateUserLoginInfo(Convert.toStr(loginId), loginModel.getDevice());
        }
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        if(SaClientTypeEnum.B.getValue().equals(loginType)) {
            // 记录B端登出日志
            SaBaseLoginUser saBaseLoginUser = loginUserApi.getUserById(Convert.toStr(loginId));
            if(ObjectUtil.isNotEmpty(saBaseLoginUser)) {
                devLogApi.executeLogoutLog(saBaseLoginUser.getName());
            } else {
                devLogApi.executeLogoutLog(null);
            }
        }
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        // ...
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        // ...
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        // ...
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        // ...
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        // ...
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        // ...
    }

    /** 每次Token续期时触发 */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        // ...
    }
}
