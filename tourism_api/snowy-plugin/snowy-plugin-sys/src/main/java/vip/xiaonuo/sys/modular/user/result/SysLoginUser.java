
package vip.xiaonuo.sys.modular.user.result;

import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.sys.modular.user.enums.SysUserStatusEnum;

/**
 * 登录用户对象
 *
 * @author gtc
 *
 **/
public class SysLoginUser extends SaBaseLoginUser {

    /**
     * 实现是否可以登录
     *
     * @author gtc
     *
     **/
    @Override
    public Boolean getEnabled() {
        // 仅判断状态是否正常，可自行扩展
        return getUserStatus().equals(SysUserStatusEnum.ENABLE.getValue());
    }
}
