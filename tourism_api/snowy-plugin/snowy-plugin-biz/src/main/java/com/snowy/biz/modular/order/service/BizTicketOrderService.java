
package com.snowy.biz.modular.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.order.entity.BizOrder;
import com.snowy.biz.modular.order.param.BizTicketOrderAddParam;
import com.snowy.biz.modular.order.param.BizTicketOrderEditParam;
import com.snowy.biz.modular.order.param.BizTicketOrderIdParam;
import com.snowy.biz.modular.order.param.BizTicketOrderPageParam;

import java.util.List;

/**
 * 门票订单表Service接口
 *
 * @author snowy
 *
 **/
public interface BizTicketOrderService extends IService<BizOrder> {

    /**
     * 获取门票订单表分页
     *
     * @author snowy
     *
     */
    Page<BizOrder> page(BizTicketOrderPageParam bizTicketOrderPageParam);

    /**
     * 添加门票订单表
     *
     * @author snowy
     *
     */
    void add(BizTicketOrderAddParam bizTicketOrderAddParam) throws InterruptedException;

    /**
     * 编辑门票订单表
     *
     * @author snowy
     *
     */
    void edit(BizTicketOrderEditParam bizTicketOrderEditParam);

    /**
     * 删除门票订单表
     *
     * @author snowy
     *
     */
    void delete(List<BizTicketOrderIdParam> bizTicketOrderIdParamList);

    /**
     * 获取门票订单表详情
     *
     * @author snowy
     *
     */
    BizOrder detail(BizTicketOrderIdParam bizTicketOrderIdParam);

    /**
     * 获取门票订单表详情
     *
     * @author snowy
     *
     **/
    BizOrder queryEntity(String id);

    void refund(BizTicketOrderIdParam bizTicketOrderIdParam);


    Page<BizOrder> spotpage(BizTicketOrderPageParam bizTicketOrderPageParam);
}
