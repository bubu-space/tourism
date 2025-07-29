package com.snowy.biz.core.timer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.snowy.biz.modular.order.entity.CacheOrederTick;
import com.snowy.biz.modular.ticket.entity.Ticket;
import com.snowy.biz.modular.ticket.service.TicketService;

import java.util.List;

@Configuration
@EnableScheduling
public class InventoryRefresh {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
//刷新每日库存的定时任务
    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshInventory() {
        UpdateWrapper<Ticket> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("inventory_count = max_inventory");
        ticketService.update(updateWrapper);

        List<Ticket> list = ticketService.lambdaQuery()
                .select(Ticket::getInventoryCount, Ticket::getPrice, Ticket::getStartTime, Ticket::getEndTime).list();
        list.forEach(ticket -> {
            CacheOrederTick newCacheOrderTick = CacheOrederTick.builder()
                    .inventory(ticket.getInventoryCount())
                    .tickprice(ticket.getPrice())
                    .startTime(ticket.getStartTime())
                    .endTime(ticket.getEndTime())
                    .build();
            stringRedisTemplate.opsForValue().set("spot:ticketid:" + ticket.getId(), newCacheOrderTick.paresJson());
        });
    }
}
