
package com.snowy.biz.modular.ticket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.ticket.entity.Ticket;
import com.snowy.biz.modular.ticket.param.TicketAddParam;
import com.snowy.biz.modular.ticket.param.TicketEditParam;
import com.snowy.biz.modular.ticket.param.TicketIdParam;
import com.snowy.biz.modular.ticket.param.TicketPageParam;

import java.util.List;

/**
 * 景点门票表Service接口
 *
 * @author snowy
 *
 **/
public interface TicketService extends IService<Ticket> {

    /**
     * 获取景点门票表分页
     *
     * @author snowy
     *
     */
    Page<Ticket> page(TicketPageParam ticketPageParam);

    /**
     * 添加景点门票表
     *
     * @author snowy
     *
     */
    void add(TicketAddParam ticketAddParam);

    /**
     * 编辑景点门票表
     *
     * @author snowy
     *
     */
    void edit(TicketEditParam ticketEditParam);

    /**
     * 删除景点门票表
     *
     * @author snowy
     *
     */
    void delete(List<TicketIdParam> ticketIdParamList);

    /**
     * 获取景点门票表详情
     *
     * @author snowy
     *
     */
    Ticket detail(TicketIdParam ticketIdParam);

    /**
     * 获取景点门票表详情
     *
     * @author snowy
     *
     **/
    Ticket queryEntity(String id);

    void reduceInventory(String ticketId, Integer ticketCount);

    void refreshInventory(TicketIdParam ticketIdParam);
}
