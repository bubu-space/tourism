
package vip.xiaonuo.biz.modular.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.order.entity.BizOrder;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderAddParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderEditParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderIdParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderPageParam;

import java.util.List;

/**
 * 门票订单表Service接口
 *
 * @author gtc
 *
 **/
public interface BizTicketOrderService extends IService<BizOrder> {

    /**
     * 获取门票订单表分页
     *
     * @author gtc
     *
     */
    Page<BizOrder> page(BizTicketOrderPageParam bizTicketOrderPageParam);

    /**
     * 添加门票订单表
     *
     * @author gtc
     *
     */
    void add(BizTicketOrderAddParam bizTicketOrderAddParam) throws InterruptedException;

    /**
     * 编辑门票订单表
     *
     * @author gtc
     *
     */
    void edit(BizTicketOrderEditParam bizTicketOrderEditParam);

    /**
     * 删除门票订单表
     *
     * @author gtc
     *
     */
    void delete(List<BizTicketOrderIdParam> bizTicketOrderIdParamList);

    /**
     * 获取门票订单表详情
     *
     * @author gtc
     *
     */
    BizOrder detail(BizTicketOrderIdParam bizTicketOrderIdParam);

    /**
     * 获取门票订单表详情
     *
     * @author gtc
     *
     **/
    BizOrder queryEntity(String id);

    void refund(BizTicketOrderIdParam bizTicketOrderIdParam);


    Page<BizOrder> spotpage(BizTicketOrderPageParam bizTicketOrderPageParam);
}
