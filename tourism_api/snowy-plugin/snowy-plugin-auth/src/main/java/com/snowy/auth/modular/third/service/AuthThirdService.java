
package com.snowy.auth.modular.third.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthCallback;
import com.snowy.auth.modular.third.entity.AuthThirdUser;
import com.snowy.auth.modular.third.param.AuthThirdCallbackParam;
import com.snowy.auth.modular.third.param.AuthThirdRenderParam;
import com.snowy.auth.modular.third.param.AuthThirdUserPageParam;
import com.snowy.auth.modular.third.result.AuthThirdRenderResult;

/**
 * 第三方登录Service接口
 *
 * @author snowy
 * 
 **/
public interface AuthThirdService extends IService<AuthThirdUser> {

    /**
     * 第三方登录页面渲染，返回授权结果
     *
     * @author snowy
     * 
     **/
    AuthThirdRenderResult render(AuthThirdRenderParam authThirdRenderParam);

    /**
     * 第三方登录授权回调，返回登录token
     *
     * @author snowy
     * 
     **/
    String callback(AuthThirdCallbackParam authThirdCallbackParam, AuthCallback authCallback);

    /**
     * 获取三方用户分页
     *
     * @author snowy
     * 
     */
    Page<AuthThirdUser> page(AuthThirdUserPageParam authThirdUserPageParam);
}
