
package com.snowy.sys.modular.position.controller;

import cn.hutool.core.lang.tree.Tree;
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
import com.snowy.sys.modular.position.entity.SysPosition;
import com.snowy.sys.modular.position.param.*;
import com.snowy.sys.modular.position.service.SysPositionService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 职位控制器
 *
 * @author snowy
 *
 */
@Api(tags = "职位控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 2)
@RestController
@Validated
public class SysPositionController {

    @Resource
    private SysPositionService sysPositionService;

    /**
     * 获取职位分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取职位分页")
    @GetMapping("/sys/position/page")
    public CommonResult<Page<SysPosition>> page(SysPositionPageParam sysPositionPageParam) {
        return CommonResult.data(sysPositionService.page(sysPositionPageParam));
    }

    /**
     * 添加职位
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加职位")
    @CommonLog("添加职位")
    @PostMapping("/sys/position/add")
    public CommonResult<String> add(@RequestBody @Valid SysPositionAddParam sysPositionAddParam) {
        sysPositionService.add(sysPositionAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑职位
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑职位")
    @CommonLog("编辑职位")
    @PostMapping("/sys/position/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysPositionEditParam sysPositionEditParam) {
        sysPositionService.edit(sysPositionEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除职位
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除职位")
    @CommonLog("删除职位")
    @PostMapping("/sys/position/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<SysPositionIdParam> sysPositionIdParamList) {
        sysPositionService.delete(sysPositionIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取职位详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取职位详情")
    @GetMapping("/sys/position/detail")
    public CommonResult<SysPosition> detail(@Valid SysPositionIdParam sysPositionIdParam) {
        return CommonResult.data(sysPositionService.detail(sysPositionIdParam));
    }

    /* ====职位部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取组织树选择器")
    @GetMapping("/sys/position/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysPositionService.orgTreeSelector());
    }

    /**
     * 获取职位选择器
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取职位选择器")
    @GetMapping("/sys/position/positionSelector")
    public CommonResult<Page<SysPosition>> positionSelector(SysPositionSelectorPositionParam sysPositionSelectorPositionParam) {
        return CommonResult.data(sysPositionService.positionSelector(sysPositionSelectorPositionParam));
    }
}
