
package com.snowy.biz.modular.bizheritage.controller;

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
import com.snowy.biz.modular.bizheritage.entity.BizHeritage;
import com.snowy.biz.modular.bizheritage.param.BizHeritageAddParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritageEditParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritageIdParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritagePageParam;
import com.snowy.biz.modular.bizheritage.service.BizHeritageService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 历史遗产动态表控制器
 *
 * @author snowy
 *
 */
@Api(tags = "历史遗产动态管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizHeritageController {

    @Resource
    private BizHeritageService bizHeritageService;

    /**
     * 获取历史遗产动态表分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/bizheritage/page")
    public CommonResult<Page<BizHeritage>> page(BizHeritagePageParam bizHeritagePageParam) {
        return CommonResult.data(bizHeritageService.page(bizHeritagePageParam));
    }

    /**
     * 添加历史遗产动态表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("新增")
    @PostMapping("/biz/bizheritage/add")
    public CommonResult<String> add(@RequestBody @Valid BizHeritageAddParam bizHeritageAddParam) {
        bizHeritageService.add(bizHeritageAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑历史遗产动态表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑历史遗产动态表")
    @PostMapping("/biz/bizheritage/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizHeritageEditParam bizHeritageEditParam) {
        bizHeritageService.edit(bizHeritageEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除历史遗产动态表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除历史遗产动态表")
    @PostMapping("/biz/bizheritage/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizHeritageIdParam> bizHeritageIdParamList) {
        bizHeritageService.delete(bizHeritageIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取历史遗产动态表详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/bizheritage/detail")
    public CommonResult<BizHeritage> detail(@Valid BizHeritageIdParam bizHeritageIdParam) {
        return CommonResult.data(bizHeritageService.detail(bizHeritageIdParam));
    }
}
