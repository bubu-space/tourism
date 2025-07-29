package com.snowy.client.modular.wxplan.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.snowy.auth.core.util.StpClientUtil;
import com.snowy.client.modular.wxplan.entity.Plan;
import com.snowy.client.modular.wxplan.param.PlanEditParam;
import com.snowy.client.modular.wxplan.param.PlanPageParam;
import com.snowy.client.modular.wxplan.service.WxplanService;
import com.snowy.common.pojo.CommonResult;

@RestController
@Api(tags = "微信用户计划")
@ApiSupport(author = "SNOWY_TEAM", order = 4)
@RequestMapping("/client/c/plan")
public class WxPlanControl {
    @Autowired
    private WxplanService planservice;


    @GetMapping("/getplan")
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页获取计划")
    public CommonResult<Page<Plan>> getPlan(PlanPageParam planPageparam) {
        return CommonResult.data(planservice.planPage(planPageparam));
    }

    @GetMapping("/getplanbyid")
    @ApiOperationSupport(order = 3)
    @ApiOperation("根据id获取计划")
    public CommonResult<Plan> getPlanById(@RequestParam("id") String id) {
        Plan plan = planservice.getById(id);
        return CommonResult.data(plan);
    }

    @GetMapping("/deleteplan")
    @ApiOperationSupport(order = 4)
    @ApiOperation("根据id删除计划")
    public CommonResult<String> deletePlan(@RequestParam("id") String id) {
        planservice.removeById(id);
        return CommonResult.ok("删除计划成功");
    }

    @PostMapping ("/updateplan")
    @ApiOperationSupport(order = 5)
    @ApiOperation("根据id修改计划")
    public CommonResult<String> updatePlan(@RequestBody @Validated PlanEditParam plan) {
        Plan byId = planservice.getById(plan.getId());
        BeanUtil.copyProperties(plan, byId);
        planservice.updateById(byId);
        return CommonResult.ok("修改计划成功");
    }



    @PostMapping("/saveplan")
    @ApiOperationSupport(order = 2)
    @ApiOperation("保存计划")
    public CommonResult<String> getPlan(@RequestBody Plan plan) {
        if (plan == null) {
            return CommonResult.ok("获取计划失败");
        }
        if (plan.getTitle() == null) {
            return CommonResult.ok("计划标题不能为空");
        }
        if (plan.getContent() == null) {
            return CommonResult.ok("计划内容不能为空");
        }
        String loginIdAsString = StpClientUtil.getLoginIdAsString();
        plan.setUserId(loginIdAsString);
        plan.setStatus("未完成");
        planservice.save(plan);
        return CommonResult.ok("保存计划成功");
    }
}
