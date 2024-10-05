package vip.xiaonuo.biz.modular.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.order.entity.BizUserTicket;
import vip.xiaonuo.biz.modular.order.entity.UserTickInfo;
import vip.xiaonuo.biz.modular.order.mapper.BizUserTicketMapper;
import vip.xiaonuo.biz.modular.order.service.BizUserTicketService;

import java.util.List;

@Service
public class BizUserTicketServiceImpl extends ServiceImpl<BizUserTicketMapper, BizUserTicket> implements BizUserTicketService {
    @Override
    public void add(UserTickInfo userTickInfo, String orderid, String ticketid) {
        try {
            BizUserTicket bizUserTicket = BeanUtil.copyProperties(userTickInfo, BizUserTicket.class);
            bizUserTicket.setOrderId(orderid);
            bizUserTicket.setTicketId(ticketid);
            bizUserTicket.setTicketCode(UUID.fastUUID().toString());
            bizUserTicket.setTicketStatus("USEED");
            this.save(bizUserTicket);
        } catch (Exception e) {
            log.error("添加用户门票信息失败", e);
        }
    }

    @Override
    public List<BizUserTicket> byorder(String orderId) {
        LambdaQueryWrapper<BizUserTicket> bizUserTicketLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bizUserTicketLambdaQueryWrapper.eq(BizUserTicket::getOrderId, orderId);
        return this.list(bizUserTicketLambdaQueryWrapper);
    }
}
