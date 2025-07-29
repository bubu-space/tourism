package com.snowy.biz.modular.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.order.entity.BizUserTicket;
import com.snowy.biz.modular.order.entity.UserTickInfo;

import java.util.List;

public interface BizUserTicketService extends IService<BizUserTicket> {
    void add(UserTickInfo userTickInfo, String orderid, String ticketid);

    List<BizUserTicket> byorder(String orderId);
}
