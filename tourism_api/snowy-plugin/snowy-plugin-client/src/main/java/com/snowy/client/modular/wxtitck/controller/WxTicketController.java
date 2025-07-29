package com.snowy.client.modular.wxtitck.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.biz.modular.ticket.entity.Ticket;
import com.snowy.biz.modular.ticket.param.TicketIdParam;
import com.snowy.biz.modular.ticket.param.TicketPageParam;
import com.snowy.biz.modular.ticket.service.TicketService;
import com.snowy.common.pojo.CommonResult;

import javax.annotation.Resource;
import javax.validation.Valid;

@RequestMapping("/client/c/wxticket")
@RestController
@Api(tags = "微信门票")
@ApiSupport(author = "SNOWY_TEAM", order = 6)
public class WxTicketController {

    @Resource
    private TicketService ticketService;

    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/page")
    public CommonResult<Page<Ticket>> page(TicketPageParam ticketPageParam) {
        return CommonResult.data(ticketService.page(ticketPageParam));
    }


    @ApiOperationSupport(order = 2)
    @ApiOperation("详情")
    @GetMapping("info")
    public CommonResult<Ticket> detail(@Valid TicketIdParam ticketIdParam) {
        return CommonResult.data(ticketService.detail(ticketIdParam));
    }
}
