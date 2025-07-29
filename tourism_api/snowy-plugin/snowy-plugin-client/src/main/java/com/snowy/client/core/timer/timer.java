package com.snowy.client.core.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.snowy.biz.modular.order.service.BizTicketOrderService;
import com.snowy.client.modular.wxplan.entity.Content;
import com.snowy.client.modular.wxplan.service.WxplanService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class timer {
    @Autowired
    private WxplanService wxplanService;

    @Autowired
    private BizTicketOrderService bizTicketOrderService;
    private ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建一个固定大小的线程池

    //检查用户计划是否完成的定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    public void action() {
        wxplanService.lambdaQuery().list().stream().forEach(plan -> CompletableFuture.runAsync(() -> {
            List<Content> contentList = plan.getContent();
            if (contentList.isEmpty()) {
                return;
            }
            LocalDateTime latestDateTime = contentList.stream()
                    .max(Comparator.comparing(Content::getDateTime))
                    .map(Content::getDateTime)
                    .orElse(LocalDateTime.MIN);

            if (LocalDateTime.now().isAfter(latestDateTime)) {
                plan.setStatus("已完成");
                wxplanService.updateById(plan);
            }
        }, executorService));
    }
    //判断评价的任务
    @Scheduled(cron = "0 * * * * ?")    //0 0 0 * * ? 每日凌晨更新
    public void setOrderStatus(){
        System.out.println("执行任务 = " + "执行任务");
        bizTicketOrderService.lambdaQuery().list().stream().forEach(order -> CompletableFuture.runAsync(() -> {
//            if (!order.getReservationTime().after(new Date())) {
             if (order.getReservationTime().before(new Date()) && order.getStatus().equals("已支付")) {
                order.setStatus("未评价");
                bizTicketOrderService.updateById(order);
                            System.out.println("更新状态： = " + "更新订单状态");
            }
        }));
    }

}
