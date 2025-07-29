
package com.snowy.dev.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 日志API
 *
 * @author snowy
 *
 */
public interface DevLogApi {

    /**
     * 记录登录日志
     *
     * @author snowy
     *
     */
    void executeLoginLog(String userName);

    /**
     * 记录登出日志
     *
     * @author snowy
     *
     */
    void executeLogoutLog(String userName);

    /**
     * 获取当前用户的访问日志列表
     *
     * @author snowy
     *
     */
    List<JSONObject> currentUserVisLogList();

    /**
     * 获取当前用户的操作日志列表
     *
     * @author snowy
     *
     */
    List<JSONObject> currentUserOpLogList();
}
