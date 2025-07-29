
package com.snowy.dev.modular.log.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.dev.modular.log.entity.DevLog;
import com.snowy.dev.modular.log.param.DevLogDeleteParam;
import com.snowy.dev.modular.log.param.DevLogIdParam;
import com.snowy.dev.modular.log.param.DevLogPageParam;
import com.snowy.dev.modular.log.result.DevLogOpBarChartDataResult;
import com.snowy.dev.modular.log.result.DevLogOpPieChartDataResult;
import com.snowy.dev.modular.log.result.DevLogVisLineChartDataResult;
import com.snowy.dev.modular.log.result.DevLogVisPieChartDataResult;
import com.snowy.dev.modular.log.service.DevLogService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 日志控制器
 *
 * @author snowy
 *
 */
@Api(tags = "日志控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 8)
@RestController
@Validated
public class DevLogController {

    @Resource
    private DevLogService devLogService;

    /**
     * 获取日志分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取日志分页")
    @GetMapping("/dev/log/page")
    public CommonResult<Page<DevLog>> page(DevLogPageParam devLogPageParam) {
        return CommonResult.data(devLogService.page(devLogPageParam));
    }

    /**
     * 清空日志
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("清空日志")
    @CommonLog("清空日志")
    @PostMapping("/dev/log/delete")
    public CommonResult<String> delete(@RequestBody DevLogDeleteParam devLogDeleteParam) {
        devLogService.delete(devLogDeleteParam);
        return CommonResult.ok();
    }

    /**
     * 获取访问日志折线图数据
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("获取访问日志折线图数据")
    @GetMapping("/dev/log/vis/lineChartData")
    public CommonResult<List<DevLogVisLineChartDataResult>> visLogLineChartData() {
        return CommonResult.data(devLogService.visLogLineChartData());
    }

    /**
     * 获取访问日志饼状图数据
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("获取访问日志饼状图数据")
    @GetMapping("/dev/log/vis/pieChartData")
    public CommonResult<List<DevLogVisPieChartDataResult>> visLogPieChartData() {
        return CommonResult.data(devLogService.visLogPieChartData());
    }

    /**
     * 获取操作日志柱状图数据
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取操作日志柱状图数据")
    @GetMapping("/dev/log/op/barChartData")
    public CommonResult<List<DevLogOpBarChartDataResult>> opLogBarChartData() {
        return CommonResult.data(devLogService.opLogBarChartData());
    }

    /**
     * 获取操作日志饼状图数据
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取操作日志饼状图数据")
    @GetMapping("/dev/log/op/pieChartData")
    public CommonResult<List<DevLogOpPieChartDataResult>> opLogPieChartData() {
        return CommonResult.data(devLogService.opLogPieChartData());
    }

    /**
     * 依据id获取单条日志详情
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("依据id获取日志详情")
    @GetMapping("/dev/log/detail")
    public CommonResult<DevLog> detail(@Valid DevLogIdParam devLogIdParam) {
        return CommonResult.data(devLogService.detail(devLogIdParam));
    }

}
