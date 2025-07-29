package com.snowy.client.modular.wxrecommend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.biz.modular.bizrecommend.entity.BizRecommend;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendIdParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendPageParam;
import com.snowy.biz.modular.bizrecommend.service.BizRecommendService;
import com.snowy.common.pojo.CommonResult;

import javax.validation.Valid;

@Api(tags = "用户推荐")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@RequestMapping("/client/c/recommend")
@Validated
public class WxRecommendController {

    @Autowired
    private BizRecommendService recommendService;

    @GetMapping("/getrecommend")
    @ApiOperation("获取推荐")
    @ApiOperationSupport(order = 1)
    public CommonResult<Page<BizRecommend>>  getRecommend(BizRecommendPageParam bizRecommendPageParam) {
        return CommonResult.data(recommendService.page(bizRecommendPageParam));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("详情")
    @GetMapping("/info")
    public CommonResult<BizRecommend> detail(@Valid BizRecommendIdParam bizRecommendIdParam) {
        return CommonResult.data(recommendService.detail(bizRecommendIdParam));
    }
}
