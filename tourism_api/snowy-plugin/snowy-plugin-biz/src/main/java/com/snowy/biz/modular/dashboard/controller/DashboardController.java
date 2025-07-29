package com.snowy.biz.modular.dashboard.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.biz.modular.dashboard.param.WeekViewId;
import com.snowy.biz.modular.dashboard.result.DashBoard;
import com.snowy.biz.modular.dashboard.result.SalesTrend;
import com.snowy.biz.modular.dashboard.service.DashboardService;
import com.snowy.common.pojo.CommonResult;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/biz/dashboard")
@Api(tags = "数据看板")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;
    @GetMapping("/view")
    @ApiOperation("查看数据看板")
    @ApiOperationSupport(order = 1)
    public CommonResult<DashBoard> dashboard() {
        DashBoard dashboard = dashboardService.dashboard();
        return CommonResult.data(dashboard);
    }

    @GetMapping("/weekview")
    @ApiOperation("查看周数据看板")
    @ApiOperationSupport(order = 2)
    public CommonResult<List<SalesTrend>> weekdashboard(@Valid WeekViewId id) {
        List<SalesTrend> salesTrends = dashboardService.weekview(id.getId());
        return CommonResult.data(salesTrends);
    }
}
