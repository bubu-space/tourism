
package com.snowy.biz.modular.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.snowy.biz.modular.comment.entity.BizComment;
import com.snowy.biz.modular.comment.param.BizCommentAddParam;
import com.snowy.biz.modular.comment.param.BizCommentEditParam;
import com.snowy.biz.modular.comment.param.BizCommentIdParam;
import com.snowy.biz.modular.comment.param.BizCommentPageParam;
import com.snowy.biz.modular.comment.result.PageComment;
import com.snowy.biz.modular.comment.service.BizCommentService;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.common.pojo.CommonValidList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 评价留言表控制器
 *
 * @author snowy
 *
 */
@Api(tags = "评价留言表控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizCommentController {

    @Resource
    private BizCommentService bizCommentService;

    /**
     * 获取评价留言表分页
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取评价留言表分页")
    @GetMapping("/biz/comment/page")
    public CommonResult<Page<PageComment>> page(BizCommentPageParam bizCommentPageParam) {
        return CommonResult.data(bizCommentService.page(bizCommentPageParam));
    }

    /**
     * 添加评价留言表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加评价留言表")
    @CommonLog("添加评价留言表")
    @PostMapping("/biz/comment/add")
    public CommonResult<String> add(@RequestBody @Valid BizCommentAddParam bizCommentAddParam) {
        bizCommentService.add(bizCommentAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑评价留言表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑评价留言表")
    @CommonLog("编辑评价留言表")
    @PostMapping("/biz/comment/edit")
    public CommonResult<String> edit(@RequestBody BizCommentEditParam bizCommentEditParam) {
        bizCommentService.edit(bizCommentEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除评价留言表
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除评价留言表")
    @CommonLog("删除评价留言表")
    @PostMapping("/biz/comment/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizCommentIdParam> bizCommentIdParamList) {
        bizCommentService.delete(bizCommentIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取评价留言表详情
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取评价留言表详情")
    @GetMapping("/biz/comment/detail")
    public CommonResult<BizComment> detail(@Valid BizCommentIdParam bizCommentIdParam) {
        return CommonResult.data(bizCommentService.detail(bizCommentIdParam));
    }
}
