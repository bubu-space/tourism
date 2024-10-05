package vip.xiaonuo.client.core.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vip.xiaonuo.biz.modular.order.service.BizTicketOrderService;
import vip.xiaonuo.client.modular.wxplan.entity.Content;
import vip.xiaonuo.client.modular.wxplan.service.WxplanService;

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
        System.out.println("定时任务执行");
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
    @Scheduled(cron = "0 0 0 * * ?")
    public void setOrderStatus(){
        bizTicketOrderService.lambdaQuery().list().stream().forEach(order -> CompletableFuture.runAsync(() -> {
            if (!order.getReservationTime().after(new Date()) && order.getReservationTime().equals(new Date())) {
                order.setStatus("未评价");
                bizTicketOrderService.updateById(order);
            }
        }));
    }

}
