
package com.snowy.biz.modular.activitie.controller;

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
import com.snowy.common.pojo.CommonValidList;
import com.snowy.biz.modular.activitie.entity.BizActivitie;
import com.snowy.biz.modular.activitie.param.BizActivitieAddParam;
import com.snowy.biz.modular.activitie.param.BizActivitieEditParam;
import com.snowy.biz.modular.activitie.param.BizActivitieIdParam;
import com.snowy.biz.modular.activitie.param.BizActivitiePageParam;
import com.snowy.biz.modular.activitie.service.BizActivitieService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 景点活动表控制器
 *
 * @author snowy
 *
 */
@Api(tags = "景点活动管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizActivitieController {

    @Resource
    private BizActivitieService bizActivitieService;

    /**
     * 获取景点活动表分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/activitie/page")
    public CommonResult<Page<BizActivitie>> page(BizActivitiePageParam bizActivitiePageParam) {
        return CommonResult.data(bizActivitieService.page(bizActivitiePageParam));
    }

    /**
     * 添加景点活动表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加景点活动表")
    @PostMapping("/biz/activitie/add")
    public CommonResult<String> add(@RequestBody @Valid BizActivitieAddParam bizActivitieAddParam) {
        bizActivitieService.add(bizActivitieAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑景点活动表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑景点活动表")
    @PostMapping("/biz/activitie/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizActivitieEditParam bizActivitieEditParam) {
        bizActivitieService.edit(bizActivitieEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除景点活动表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除景点活动表")
    @PostMapping("/biz/activitie/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizActivitieIdParam> bizActivitieIdParamList) {
        bizActivitieService.delete(bizActivitieIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取景点活动表详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/activitie/detail")
    public CommonResult<BizActivitie> detail(@Valid BizActivitieIdParam bizActivitieIdParam) {
        return CommonResult.data(bizActivitieService.detail(bizActivitieIdParam));
    }
}
