
package com.snowy.dev.modular.monitor.service;

import com.snowy.dev.modular.monitor.result.DevMonitorServerResult;

/**
 * 监控Service接口
 *
 * @author snowy
 * 
 */
public interface DevMonitorService {

    /**
     * 获取服务器监控信息
     *
     * @author snowy
     * 
     */
    DevMonitorServerResult serverInfo();

    /**
     * 获取服务器网络情况
     *
     * @author snowy
     * 
     */
    DevMonitorServerResult networkInfo();
}
