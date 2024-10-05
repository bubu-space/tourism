package vip.xiaonuo.biz.modular.history.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.history.entity.BizHistory;
import vip.xiaonuo.biz.modular.history.param.BizHistoryAddParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryEditParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryIdParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryPageParam;
import vip.xiaonuo.biz.modular.history.service.BizHistoryService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Api(tags = "历史动态管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
public class BizHistoryController {

    @Resource
    private BizHistoryService historyService;

    /**
     * 获取景点表分页
     *
     * @author gtc
     *  2024/03/21 17:58
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/history/page")
    public CommonResult<Page<BizHistory>> page(BizHistoryPageParam pageParam) {
        return CommonResult.data(historyService.page(pageParam));
    }

    /**
     * 添加景点表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加历史动态")
    @PostMapping("/biz/history/add")
    public CommonResult<String> add(@RequestBody @Valid BizHistoryAddParam addParam) {
        historyService.add(addParam);
        return CommonResult.ok();
    }

    /**
     * 编辑景点表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑历史动态")
    @PostMapping("/biz/history/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizHistoryEditParam editParam) {
        historyService.edit(editParam);
        return CommonResult.ok();
    }

    /**
     * 删除景点表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除历史动态")
    @PostMapping("/biz/history/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       CommonValidList<BizHistoryIdParam> idParamList) {
        historyService.delete(idParamList);
        return CommonResult.ok();
    }

    /**
     * 获取景点表详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/history/detail")
    public CommonResult<BizHistory> detail(@Valid BizHistoryIdParam idParam) {
        return CommonResult.data(historyService.detail(idParam));
    }


}
