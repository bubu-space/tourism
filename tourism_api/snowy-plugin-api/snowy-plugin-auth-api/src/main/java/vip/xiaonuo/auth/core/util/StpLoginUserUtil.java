
package vip.xiaonuo.auth.core.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.common.util.CommonServletUtil;

import java.util.List;

/**
 * B端登录用户工具类
 *
 * @author gtc
 *
 **/
public class StpLoginUserUtil {

    /**
     * 获取当前B端登录用户
     *
     * @author gtc
     *
     **/
    public static SaBaseLoginUser getLoginUser() {
        return (SaBaseLoginUser) StpUtil.getTokenSession().get("loginUser");
    }

    /**
     * 获取当前B端登录用户的当前请求接口的数据范围
     *
     * @author gtc
     *
     **/
    public static List<String> getLoginUserDataScope() {
        List<String> resultList = CollectionUtil.newArrayList();
        getLoginUser().getDataScopeList().forEach(dataScope -> {
            if(dataScope.getApiUrl().equals(CommonServletUtil.getRequest().getServletPath())) {
                resultList.addAll(dataScope.getDataScope());
            }
        });
        return resultList;
    }
}
