
package com.snowy.auth.modular.login.service;

import com.snowy.auth.core.pojo.SaBaseClientLoginUser;
import com.snowy.auth.core.pojo.SaBaseLoginUser;
import com.snowy.auth.modular.login.param.AuthAccountPasswordLoginParam;
import com.snowy.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import com.snowy.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import com.snowy.auth.modular.login.result.AuthPicValidCodeResult;

/**
 * 登录Service接口
 *
 * @author snowy
 *
 */
public interface AuthService {

    /**
     * 获取图片验证码
     *
     * @author snowy
     *
     **/
    AuthPicValidCodeResult getPicCaptcha(String type);

    /**
     * 获取手机验证码
     *
     * @author snowy
     *
     **/
    String getPhoneValidCode(AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam, String type);

    /**
     * 账号密码登录
     *
     * @author snowy
     *
     **/
    String doLogin(AuthAccountPasswordLoginParam authAccountPasswordLoginParam, String type);

    /**
     * 手机验证码登录
     *
     * @author snowy
     *
     **/
    String doLoginByPhone(AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam, String type);

    /**
     * 获取B端登录用户信息
     *
     * @author snowy
     *
     **/
    SaBaseLoginUser getLoginUser();

    /**
     * 获取C端登录用户信息
     *
     * @author snowy
     *
     **/
    SaBaseClientLoginUser getClientLoginUser();

    /**
     * 根据用户id和客户端类型登录，用于第三方登录
     *
     * @author snowy
     *
     */
    String doLoginById(String userId, String device, String type);
}
