
package vip.xiaonuo.biz.modular.ticket.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.ticket.entity.Ticket;
import vip.xiaonuo.biz.modular.ticket.param.TicketAddParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketEditParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketIdParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketPageParam;

import java.util.List;

/**
 * 景点门票表Service接口
 *
 * @author gtc
 *
 **/
public interface TicketService extends IService<Ticket> {

    /**
     * 获取景点门票表分页
     *
     * @author gtc
     *
     */
    Page<Ticket> page(TicketPageParam ticketPageParam);

    /**
     * 添加景点门票表
     *
     * @author gtc
     *
     */
    void add(TicketAddParam ticketAddParam);

    /**
     * 编辑景点门票表
     *
     * @author gtc
     *
     */
    void edit(TicketEditParam ticketEditParam);

    /**
     * 删除景点门票表
     *
     * @author gtc
     *
     */
    void delete(List<TicketIdParam> ticketIdParamList);

    /**
     * 获取景点门票表详情
     *
     * @author gtc
     *
     */
    Ticket detail(TicketIdParam ticketIdParam);

    /**
     * 获取景点门票表详情
     *
     * @author gtc
     *
     **/
    Ticket queryEntity(String id);

    void reduceInventory(String ticketId, Integer ticketCount);

    void refreshInventory(TicketIdParam ticketIdParam);
}
