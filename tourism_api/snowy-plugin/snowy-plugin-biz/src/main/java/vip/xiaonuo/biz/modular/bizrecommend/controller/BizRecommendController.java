
package vip.xiaonuo.biz.modular.bizrecommend.controller;

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
import vip.xiaonuo.biz.modular.bizrecommend.entity.BizRecommend;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendAddParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendEditParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendIdParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendPageParam;
import vip.xiaonuo.biz.modular.bizrecommend.service.BizRecommendService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 住/礼/食推荐表控制器
 *
 * @author gtc
 * 
 */
@Api(tags = "食住行推荐管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizRecommendController {

    @Resource
    private BizRecommendService bizRecommendService;

    /**
     * 获取住/礼/食推荐表分页
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/bizrecommend/page")
    public CommonResult<Page<BizRecommend>> page(BizRecommendPageParam bizRecommendPageParam) {
        return CommonResult.data(bizRecommendService.page(bizRecommendPageParam));
    }

    /**
     * 添加住/礼/食推荐表
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("新增")
    @PostMapping("/biz/bizrecommend/add")
    public CommonResult<String> add(@RequestBody @Valid BizRecommendAddParam bizRecommendAddParam) {
        bizRecommendService.add(bizRecommendAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑住/礼/食推荐表
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑")
    @PostMapping("/biz/bizrecommend/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizRecommendEditParam bizRecommendEditParam) {
        bizRecommendService.edit(bizRecommendEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除住/礼/食推荐表
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除")
    @PostMapping("/biz/bizrecommend/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizRecommendIdParam> bizRecommendIdParamList) {
        bizRecommendService.delete(bizRecommendIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取住/礼/食推荐表详情
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/bizrecommend/detail")
    public CommonResult<BizRecommend> detail(@Valid BizRecommendIdParam bizRecommendIdParam) {
        return CommonResult.data(bizRecommendService.detail(bizRecommendIdParam));
    }
}
