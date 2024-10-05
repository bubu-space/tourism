
package vip.xiaonuo.dev.api;

import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 日志API
 *
 * @author gtc
 *
 */
public interface DevLogApi {

    /**
     * 记录登录日志
     *
     * @author gtc
     *
     */
    void executeLoginLog(String userName);

    /**
     * 记录登出日志
     *
     * @author gtc
     *
     */
    void executeLogoutLog(String userName);

    /**
     * 获取当前用户的访问日志列表
     *
     * @author gtc
     *
     */
    List<JSONObject> currentUserVisLogList();

    /**
     * 获取当前用户的操作日志列表
     *
     * @author gtc
     *
     */
    List<JSONObject> currentUserOpLogList();
}
