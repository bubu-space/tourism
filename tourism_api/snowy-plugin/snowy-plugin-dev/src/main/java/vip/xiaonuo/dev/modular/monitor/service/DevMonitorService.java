
package vip.xiaonuo.dev.modular.monitor.service;

import vip.xiaonuo.dev.modular.monitor.result.DevMonitorServerResult;

/**
 * 监控Service接口
 *
 * @author gtc
 * 
 */
public interface DevMonitorService {

    /**
     * 获取服务器监控信息
     *
     * @author gtc
     * 
     */
    DevMonitorServerResult serverInfo();

    /**
     * 获取服务器网络情况
     *
     * @author gtc
     * 
     */
    DevMonitorServerResult networkInfo();
}
