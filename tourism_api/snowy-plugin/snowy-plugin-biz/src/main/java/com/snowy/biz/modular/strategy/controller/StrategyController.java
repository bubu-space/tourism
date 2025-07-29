
package com.snowy.biz.modular.strategy.controller;

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
import com.snowy.biz.modular.strategy.entity.Strategy;
import com.snowy.biz.modular.strategy.param.StrategyAddParam;
import com.snowy.biz.modular.strategy.param.StrategyEditParam;
import com.snowy.biz.modular.strategy.param.StrategyIdParam;
import com.snowy.biz.modular.strategy.param.StrategyPageParam;
import com.snowy.biz.modular.strategy.service.StrategyService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 旅游攻略表：由用户发表控制器
 *
 * @author snowy
 *
 */
@Api(tags = "攻略管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class StrategyController {

    @Resource
    private StrategyService strategyService;

    /**
     * 获取旅游攻略表：由用户发表分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/strategy/page")
    public CommonResult<Page<Strategy>> page(StrategyPageParam strategyPageParam) {
        return CommonResult.data(strategyService.page(strategyPageParam));
    }

    /**
     * 添加旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加旅游攻略表：由用户发表")
    @PostMapping("/biz/strategy/add")
    public CommonResult<String> add(@RequestBody @Valid StrategyAddParam strategyAddParam) {
        strategyService.add(strategyAddParam);
        return CommonResult.ok();
    }

    @PostMapping("/client/c/wxstrategy/add")
    public CommonResult<String> add2(@RequestBody @Valid StrategyAddParam strategyAddParam) {
        strategyService.addForC(strategyAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑旅游攻略表：由用户发表")
    @PostMapping("/biz/strategy/edit")
    public CommonResult<String> edit(@RequestBody @Valid StrategyEditParam strategyEditParam) {
        strategyService.edit(strategyEditParam);
        return CommonResult.ok();
    }

    @PostMapping("/client/c/wxstrategy/edit")
    public CommonResult<String> edit2(@RequestBody @Valid StrategyEditParam strategyEditParam) {
        strategyService.edit(strategyEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除旅游攻略表：由用户发表")
    @PostMapping("/biz/strategy/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<StrategyIdParam> strategyIdParamList) {
        strategyService.delete(strategyIdParamList);
        return CommonResult.ok();
    }

    @PostMapping("/client/c/wxstrategy/remove")
    public CommonResult<String> delete2(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<StrategyIdParam> strategyIdParamList) {
        strategyService.delete(strategyIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/strategy/detail")
    public CommonResult<Strategy> detail(@Valid StrategyIdParam strategyIdParam) {
        return CommonResult.data(strategyService.detail(strategyIdParam));
    }

    @GetMapping("/client/c/wxstrategy/detail")
    public CommonResult<Strategy> detail2(@Valid StrategyIdParam strategyIdParam) {
        return CommonResult.data(strategyService.detail(strategyIdParam));
    }
}
