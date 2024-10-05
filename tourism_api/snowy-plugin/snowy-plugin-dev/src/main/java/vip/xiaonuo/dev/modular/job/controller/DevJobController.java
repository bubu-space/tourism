
package vip.xiaonuo.dev.modular.job.controller;

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
import vip.xiaonuo.dev.modular.job.entity.DevJob;
import vip.xiaonuo.dev.modular.job.param.*;
import vip.xiaonuo.dev.modular.job.service.DevJobService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 定时任务控制器
 *
 * @author gtc
 *
 **/
@Api(tags = "定时任务控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 7)
@RestController
@Validated
public class DevJobController {

    @Resource
    private DevJobService devJobService;
    
    /**
     * 获取定时任务分页
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取定时任务分页")
    @GetMapping("/dev/job/page")
    public CommonResult<Page<DevJob>> page(DevJobPageParam devJobPageParam) {
        return CommonResult.data(devJobService.page(devJobPageParam));
    }

    /**
     * 获取定时任务列表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取定时任务列表")
    @GetMapping("/dev/job/list")
    public CommonResult<List<DevJob>> list(DevJobListParam devJobListParam) {
        return CommonResult.data(devJobService.list(devJobListParam));
    }

    /**
     * 添加定时任务
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("添加定时任务")
    @CommonLog("添加定时任务")
    @PostMapping("/dev/job/add")
    public CommonResult<String> add(@RequestBody @Valid DevJobAddParam devJobAddParam) {
        devJobService.add(devJobAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑定时任务
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("编辑定时任务")
    @CommonLog("编辑定时任务")
    @PostMapping("/dev/job/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevJobEditParam devJobEditParam) {
        devJobService.edit(devJobEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除定时任务
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("删除定时任务")
    @CommonLog("删除定时任务")
    @PostMapping("/dev/job/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               CommonValidList<DevJobIdParam> devJobIdParamList) {
        devJobService.delete(devJobIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取定时任务详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取定时任务详情")
    @GetMapping("/dev/job/detail")
    public CommonResult<DevJob> detail(@Valid DevJobIdParam devJobIdParam) {
        return CommonResult.data(devJobService.detail(devJobIdParam));
    }

    /**
     * 停止定时任务
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 6)
    @ApiOperation("停止定时任务")
    @CommonLog("停止定时任务")
    @PostMapping("/dev/job/stopJob")
    public CommonResult<String> stopJob(@RequestBody DevJobIdParam devJobIdParam) {
        devJobService.stopJob(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 运行定时任务
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 7)
    @ApiOperation("运行定时任务")
    @CommonLog("运行定时任务")
    @PostMapping("/dev/job/runJob")
    public CommonResult<String> runJob(@RequestBody @Valid DevJobIdParam devJobIdParam) {
        devJobService.runJob(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 立即运行定时任务
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 8)
    @ApiOperation("立即运行定时任务")
    @CommonLog("立即运行定时任务")
    @PostMapping("/dev/job/runJobNow")
    public CommonResult<String> runJobNow(@RequestBody @Valid DevJobIdParam devJobIdParam) {
        devJobService.runJobNow(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 获取定时任务类
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation("获取定时任务类")
    @GetMapping("/dev/job/getActionClass")
    public CommonResult<List<String>> getActionClass() {
        return CommonResult.data(devJobService.getActionClass());
    }
}
