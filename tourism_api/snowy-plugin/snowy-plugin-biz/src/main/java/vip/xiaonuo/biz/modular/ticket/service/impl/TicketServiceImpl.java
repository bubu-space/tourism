
package vip.xiaonuo.biz.modular.ticket.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.order.entity.CacheOrederTick;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.ticket.entity.Ticket;
import vip.xiaonuo.biz.modular.ticket.mapper.TicketMapper;
import vip.xiaonuo.biz.modular.ticket.param.TicketAddParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketEditParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketIdParam;
import vip.xiaonuo.biz.modular.ticket.param.TicketPageParam;
import vip.xiaonuo.biz.modular.ticket.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 景点门票表Service接口实现类
 *
 * @author gtc
 *
 **/
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page<Ticket> page(TicketPageParam ticketPageParam) {
        QueryWrapper<Ticket> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(ticketPageParam.getName())) {
            queryWrapper.lambda().like(Ticket::getName, ticketPageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(ticketPageParam.getSpotId())) {
            queryWrapper.lambda().like(Ticket::getSpotId, ticketPageParam.getSpotId());
        }
        if(ObjectUtil.isAllNotEmpty(ticketPageParam.getSortField(), ticketPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(ticketPageParam.getSortOrder());
            queryWrapper.orderBy(true, ticketPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(ticketPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Ticket::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(TicketAddParam ticketAddParam) {
        Ticket ticket = BeanUtil.toBean(ticketAddParam, Ticket.class);
        ticket.setMaxInventory(ticketAddParam.getMaxInventory());
        ticket.setInventoryCount(ticketAddParam.getMaxInventory());
        this.save(ticket);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(TicketEditParam ticketEditParam) {
        Ticket ticket = this.queryEntity(ticketEditParam.getId());
        BeanUtil.copyProperties(ticketEditParam, ticket);
        ticket.setMaxInventory(ticketEditParam.getMaxInventory());
        ticket.setInventoryCount(ticketEditParam.getMaxInventory());
        this.updateById(ticket);
        CacheOrederTick build = CacheOrederTick.builder()
                .inventory(ticket.getInventoryCount())
                .tickprice(ticket.getPrice())
                .startTime(ticket.getStartTime())
                .endTime(ticket.getEndTime())
                .build();
        String cacheKey = "spot:ticketid:" + ticket.getId();
        stringRedisTemplate.opsForValue().set(cacheKey, build.paresJson());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<TicketIdParam> idParamList) {
        // 执行删除
        List<String> list = idParamList.stream().map(TicketIdParam::getId).collect(Collectors.toList());
        this.removeByIds(list);
        stringRedisTemplate.delete(list.stream().map(id -> "spot:ticketid:" + id).collect(Collectors.toList()));
    }

    @Override
    public Ticket detail(TicketIdParam ticketIdParam) {
        return this.queryEntity(ticketIdParam.getId());
    }

    @Override
    public Ticket queryEntity(String id) {
        Ticket ticket = this.getById(id);
        if(ObjectUtil.isEmpty(ticket)) {
            throw new CommonException("景点门票表不存在，id值为：{}", id);
        }
        return ticket;
    }

    @Override
    public void reduceInventory(String ticketId, Integer ticketCount) {
        Ticket ticket = this.getById(ticketId);
        if (ticket == null) {
            throw new CommonException("门票不存在");
        }

        // 检查库存是否足够
        if (ticket.getInventoryCount() < ticketCount) {
            throw new CommonException("门票库存不足");
        }

        // 减少库存
        Long newInventory = ticket.getInventoryCount() - ticketCount;
        ticket.setInventoryCount(newInventory);
        this.updateById(ticket);

    }

    @Override
    public void refreshInventory(TicketIdParam ticketIdParam) {
        UpdateWrapper<Ticket> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("inventory_count = max_inventory");
        this.update(updateWrapper);
        List<Ticket> list = this.lambdaQuery()
                .select(Ticket::getId, Ticket::getInventoryCount, Ticket::getPrice, Ticket::getStartTime, Ticket::getEndTime).list();
        list.forEach(ticket -> {
            CacheOrederTick newCacheOrderTick = CacheOrederTick.builder()
                    .inventory(ticket.getInventoryCount())
                    .tickprice(ticket.getPrice())
                    .startTime(ticket.getStartTime())
                    .endTime(ticket.getEndTime())
                    .build();
            stringRedisTemplate.opsForValue().set("spot:ticketid:" + ticketIdParam.getId(), newCacheOrderTick.paresJson());
        });
    }
}
