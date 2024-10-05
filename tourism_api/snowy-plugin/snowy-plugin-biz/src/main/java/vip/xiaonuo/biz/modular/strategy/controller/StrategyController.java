
package vip.xiaonuo.biz.modular.strategy.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;
import vip.xiaonuo.biz.modular.strategy.entity.Strategy;
import vip.xiaonuo.biz.modular.strategy.param.StrategyAddParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyEditParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyIdParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyPageParam;
import vip.xiaonuo.biz.modular.strategy.service.StrategyService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 旅游攻略表：由用户发表控制器
 *
 * @author gtc
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
     * @author gtc
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
     * @author gtc
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

    /**
     * 编辑旅游攻略表：由用户发表
     *
     * @author gtc
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

    /**
     * 删除旅游攻略表：由用户发表
     *
     * @author gtc
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

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/strategy/detail")
    public CommonResult<Strategy> detail(@Valid StrategyIdParam strategyIdParam) {
        return CommonResult.data(strategyService.detail(strategyIdParam));
    }
}
