
package vip.xiaonuo.biz.modular.ticket.controller;

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
import vip.xiaonuo.biz.modular.ticket.entity.Ticket;
import vip.xiaonuo.biz.modular.ticket.param.TicketAddParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketEditParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketIdParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketPageParam;
import vip.xiaonuo.biz.modular.ticket.service.TicketService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 景点门票表控制器
 *
 * @author gtc
 * 
 */
@Api(tags = "门票管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class TicketController {

    @Resource
    private TicketService ticketService;

    /**
     * 获取景点门票表分页
     *
     * @author gtc
     * page 方法：用于获取景点门票表的分页数据。注释描述了该方法接受的参数和返回的结果。
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/ticket/page")
    public CommonResult<Page<Ticket>> page(TicketPageParam ticketPageParam) {
        return CommonResult.data(ticketService.page(ticketPageParam));
    }

    /**
     * 添加景点门票表
     *
     * @author gtc
     * add 方法：用于添加新的景点门票。注释描述了该方法接受的参数以及成功添加后返回的结果。
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加")
    @CommonLog("添加景点门票表")
    @PostMapping("/biz/ticket/add")
    public CommonResult<String> add(@RequestBody @Valid TicketAddParam ticketAddParam) {
        ticketService.add(ticketAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑景点门票表
     *
     * @author gtc
     * edit 方法负责编辑门票信息。它接受一个 TicketEditParam 对象作为输入，该对象包含要编辑的信息。
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑")
    @CommonLog("编辑景点门票表")
    @PostMapping("/biz/ticket/edit")
    public CommonResult<String> edit(@RequestBody @Valid TicketEditParam ticketEditParam) {
        ticketService.edit(ticketEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除景点门票表
     *delete 方法删除门票信息。它接受一个包含待删除门票 ID 信息的 TicketIdParam 对象列表。
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除景点门票表")
    @PostMapping("/biz/ticket/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<TicketIdParam> ticketIdParamList) {
        ticketService.delete(ticketIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取景点门票表详情
     *detail 方法获取门票的详细信息。它接受一个 TicketIdParam 对象作为输入，指定要检索的门票的 ID。
     * @author gtc
     * refreshInventory 方法用于刷新门票的库存数量。它接受一个 TicketIdParam 对象，指定需要刷新库存的门票的 ID。
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/ticket/detail")
    public CommonResult<Ticket> detail(@Valid TicketIdParam ticketIdParam) {
        return CommonResult.data(ticketService.detail(ticketIdParam));
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation("刷新库存数量")
    @GetMapping("/biz/ticket/refreshInventory")
    public CommonResult<String> refreshInventory(@Valid TicketIdParam ticketIdParam) {
        ticketService.refreshInventory(ticketIdParam);
        return CommonResult.ok();
    }
}
