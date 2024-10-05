
package vip.xiaonuo.biz.modular.spot.controller;

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
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.biz.modular.spot.param.SpotAddParam;
import vip.xiaonuo.biz.modular.spot.param.SpotEditParam;
import vip.xiaonuo.biz.modular.spot.param.SpotIdParam;
import vip.xiaonuo.biz.modular.spot.param.SpotPageParam;
import vip.xiaonuo.biz.modular.spot.result.Namelist;
import vip.xiaonuo.biz.modular.spot.service.SpotService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 景点表控制器
 *
 * @author gtc
 *   2024/03/21 17:58
 */
@Api(tags = "景区管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class SpotController {

    @Resource
    private SpotService spotService;

    /**
     * 获取景点表分页
     *
     * @author gtc
     *  2024/03/21 17:58
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/spot/page")
    public CommonResult<Page<Spot>> page(SpotPageParam spotPageParam) {
        return CommonResult.data(spotService.page(spotPageParam));
    }

    /**
     * 添加景点表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加景点表")
    @PostMapping("/biz/spot/add")
    public CommonResult<String> add(@RequestBody @Valid SpotAddParam spotAddParam) {
        spotService.add(spotAddParam);
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
    @CommonLog("编辑景点表")
    @PostMapping("/biz/spot/edit")
    public CommonResult<String> edit(@RequestBody @Valid SpotEditParam spotEditParam) {
        spotService.edit(spotEditParam);
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
    @CommonLog("删除景点表")
    @PostMapping("/biz/spot/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<SpotIdParam> spotIdParamList) {
        spotService.delete(spotIdParamList);
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
    @GetMapping("/biz/spot/detail")
    public CommonResult<Spot> detail(@Valid SpotIdParam spotIdParam) {
        return CommonResult.data(spotService.detail(spotIdParam));
    }


    @ApiOperationSupport(order = 6)
    @ApiOperation("景区列表")
    @GetMapping("/biz/spot/namelist")
    public CommonResult<List<Namelist>> namelist() {
        return CommonResult.data(spotService.namelist());
    }


}
