
package vip.xiaonuo.auth.modular.login.service;

import vip.xiaonuo.auth.core.pojo.SaBaseClientLoginUser;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.auth.modular.login.param.AuthAccountPasswordLoginParam;
import vip.xiaonuo.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import vip.xiaonuo.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import vip.xiaonuo.auth.modular.login.result.AuthPicValidCodeResult;

/**
 * 登录Service接口
 *
 * @author gtc
 *
 */
public interface AuthService {

    /**
     * 获取图片验证码
     *
     * @author gtc
     *
     **/
    AuthPicValidCodeResult getPicCaptcha(String type);

    /**
     * 获取手机验证码
     *
     * @author gtc
     *
     **/
    String getPhoneValidCode(AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam, String type);

    /**
     * 账号密码登录
     *
     * @author gtc
     *
     **/
    String doLogin(AuthAccountPasswordLoginParam authAccountPasswordLoginParam, String type);

    /**
     * 手机验证码登录
     *
     * @author gtc
     *
     **/
    String doLoginByPhone(AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam, String type);

    /**
     * 获取B端登录用户信息
     *
     * @author gtc
     *
     **/
    SaBaseLoginUser getLoginUser();

    /**
     * 获取C端登录用户信息
     *
     * @author gtc
     *
     **/
    SaBaseClientLoginUser getClientLoginUser();

    /**
     * 根据用户id和客户端类型登录，用于第三方登录
     *
     * @author gtc
     *
     */
    String doLoginById(String userId, String device, String type);
}
