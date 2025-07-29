
package com.snowy.biz.modular.user.controller;

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
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.common.pojo.CommonValidList;
import com.snowy.biz.modular.user.entity.BizUser;
import com.snowy.biz.modular.user.param.BizUserAddParam;
import com.snowy.biz.modular.user.param.BizUserEditParam;
import com.snowy.biz.modular.user.param.BizUserIdParam;
import com.snowy.biz.modular.user.param.BizUserPageParam;
import com.snowy.biz.modular.user.service.BizUserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 用户表控制器
 *
 * @author snowy
 *
 */
@Api(tags = "微信用户管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizUserController {

    @Resource
    private BizUserService bizUserService;

    /**
     * 获取用户表分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/user/page")
    public CommonResult<Page<BizUser>> page(BizUserPageParam bizUserPageParam) {
        return CommonResult.data(bizUserService.page(bizUserPageParam));
    }

    /**
     * 添加用户表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加用户表")
    @SaCheckPermission("/biz/user/add")
    @PostMapping("/biz/user/add")
    public CommonResult<String> add(@RequestBody @Valid BizUserAddParam bizUserAddParam) {
        bizUserService.add(bizUserAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑用户表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑用户表")
    @SaCheckPermission("/biz/user/edit")
    @PostMapping("/biz/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizUserEditParam bizUserEditParam) {
        bizUserService.edit(bizUserEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除用户表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除用户表")
    @SaCheckPermission("/biz/user/delete")
    @PostMapping("/biz/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizUserIdParam> bizUserIdParamList) {
        bizUserService.delete(bizUserIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取用户表详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @SaCheckPermission("/biz/user/detail")
    @GetMapping("/biz/user/detail")
    public CommonResult<BizUser> detail(@Valid BizUserIdParam bizUserIdParam) {
        return CommonResult.data(bizUserService.detail(bizUserIdParam));
    }
}
