
package com.snowy.auth.core.util;

import cn.hutool.core.collection.CollectionUtil;
import com.snowy.auth.core.pojo.SaBaseClientLoginUser;

import java.util.List;

/**
 * C端登录用户工具类
 *
 * @author snowy
 *
 **/
public class StpClientLoginUserUtil {

    /**
     * 获取当前C端登录用户
     *
     * @author snowy
     *
     **/
    public static SaBaseClientLoginUser getClientLoginUser() {
        return (SaBaseClientLoginUser) StpClientUtil.getTokenSession().get("loginUser");
    }

    /**
     * 获取当前C端登录用户的当前请求接口的数据范围（暂无数据范围）
     *
     * @author snowy
     *
     **/
    public static List<String> getLoginUserDataScope() {
        return CollectionUtil.newArrayList();
    }
}
