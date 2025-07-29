
package com.snowy.dev.modular.monitor.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.common.pojo.CommonResult;
import com.snowy.dev.modular.monitor.result.DevMonitorServerResult;
import com.snowy.dev.modular.monitor.service.DevMonitorService;

import javax.annotation.Resource;

/**
 * 监控控制器
 *
 * @author snowy
 * 
 **/
@Api(tags = "监控控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 9)
@RestController
@Validated
public class DevMonitorController {

    @Resource
    private DevMonitorService devMonitorService;

    /**
     * 获取服务器监控信息
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取服务器监控信息")
    @GetMapping("/dev/monitor/serverInfo")
    public CommonResult<DevMonitorServerResult> serverInfo() {
        return CommonResult.data(devMonitorService.serverInfo());
    }

    /**
     * 获取服务器网络情况
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取服务器网络情况")
    @GetMapping("/dev/monitor/networkInfo")
    public CommonResult<DevMonitorServerResult> networkInfo() {
        return CommonResult.data(devMonitorService.networkInfo());
    }
}
