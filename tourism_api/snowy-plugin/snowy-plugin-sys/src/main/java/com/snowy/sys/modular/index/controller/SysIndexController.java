
package com.snowy.sys.modular.index.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.common.pojo.CommonValidList;
import com.snowy.sys.modular.index.param.*;
import com.snowy.sys.modular.index.result.*;
import com.snowy.sys.modular.index.service.SysIndexService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 系统首页控制器
 *
 * @author snowy
 * 
 */
@Api(tags = "系统首页控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 0)
@RestController
@Validated
public class SysIndexController {

    @Resource
    private SysIndexService sysIndexService;

    /**
     * 添加当前用户日程
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("添加日程")
    @CommonLog("添加日程")
    @PostMapping("/sys/index/schedule/add")
    public CommonResult<String> addSchedule(@RequestBody @Valid SysIndexScheduleAddParam sysIndexScheduleAddParam) {
        sysIndexService.addSchedule(sysIndexScheduleAddParam);
        return CommonResult.ok();
    }

    /**
     * 删除日程
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("删除日程")
    @CommonLog("删除日程")
    @PostMapping("/sys/index/schedule/deleteSchedule")
    public CommonResult<String> deleteSchedule(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               CommonValidList<SysIndexScheduleIdParam> sysIndexScheduleIdParamList) {
        sysIndexService.deleteSchedule(sysIndexScheduleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取当前用户日程列表
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("获取日程列表")
    @GetMapping("/sys/index/schedule/list")
    public CommonResult<List<SysIndexScheduleListResult>> scheduleList(@Valid SysIndexScheduleListParam sysIndexScheduleListParam) {
        return CommonResult.data(sysIndexService.scheduleList(sysIndexScheduleListParam));
    }

    /**
     * 获取当前用户站内信列表
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("获取当前用户站内信列表")
    @GetMapping("/sys/index/message/list")
    public CommonResult<List<SysIndexMessageListResult>> messageList(SysIndexMessageListParam sysIndexMessageListParam) {
        return CommonResult.data(sysIndexService.messageList(sysIndexMessageListParam));
    }

    /**
     * 获取站内信详情
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取站内信详情")
    @GetMapping("/sys/index/message/detail")
    public CommonResult<SysIndexMessageDetailResult> messageDetail(@Valid SysIndexMessageIdParam sysIndexMessageIdParam) {
        return CommonResult.data(sysIndexService.messageDetail(sysIndexMessageIdParam));
    }

    /**
     * 站内信全部标记已读
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("站内信全部标记已读")
    @PostMapping("/sys/index/message/allMessageMarkRead")
    public CommonResult<String> allMessageMarkRead() {
        sysIndexService.allMessageMarkRead();
        return CommonResult.ok();
    }

    /**
     * 获取当前用户访问日志列表
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取当前用户访问日志列表")
    @GetMapping("/sys/index/visLog/list")
    public CommonResult<List<SysIndexVisLogListResult>> visLogList() {
        return CommonResult.data(sysIndexService.visLogList());
    }

    /**
     * 获取当前用户操作日志列表
     *
     * @author snowy
     * 
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation("获取当前用户操作日志列表")
    @GetMapping("/sys/index/opLog/list")
    public CommonResult<List<SysIndexOpLogListResult>> opLogList() {
        return CommonResult.data(sysIndexService.opLogList());
    }

    /**
     * 创建sse连接
     *
     * @author snowy
     * 
     **/
    @ApiOperationSupport(order = 9)
    @ApiOperation("创建sse连接")
    @GetMapping("/dev/message/createSseConnect")
    public SseEmitter createSseConnect(String clientId){
        return sysIndexService.createSseConnect(clientId);
    }
}
