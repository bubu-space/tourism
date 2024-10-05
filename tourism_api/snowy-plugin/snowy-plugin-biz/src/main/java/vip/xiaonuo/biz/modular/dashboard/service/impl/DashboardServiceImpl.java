package vip.xiaonuo.biz.modular.dashboard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.dashboard.param.WeekViewId;
import vip.xiaonuo.biz.modular.dashboard.result.DashBoard;
import vip.xiaonuo.biz.modular.dashboard.result.SalesTrend;
import vip.xiaonuo.biz.modular.dashboard.service.DashboardService;
import vip.xiaonuo.biz.modular.order.entity.BizOrder;
import vip.xiaonuo.biz.modular.order.mapper.BizTicketOrderMapper;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private BizTicketOrderMapper orderMapper;
    @Override
    public DashBoard dashboard() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        LambdaQueryWrapper<BizOrder> dailyQueryWrapper = new LambdaQueryWrapper<>();
        dailyQueryWrapper.ge(BizOrder::getCreateTime, now.atStartOfDay());
        dailyQueryWrapper.lt(BizOrder::getCreateTime, now.plusDays(1).atStartOfDay());
        List<BizOrder> dailyOrders = orderMapper.selectList(dailyQueryWrapper);

        LambdaQueryWrapper<BizOrder> weeklyQueryWrapper = new LambdaQueryWrapper<>();
        weeklyQueryWrapper.between(BizOrder::getCreateTime, startOfWeek, endOfWeek);
        List<BizOrder> weeklyOrders = orderMapper.selectList(weeklyQueryWrapper);

        LambdaQueryWrapper<BizOrder> totalQueryWrapper = new LambdaQueryWrapper<>();
        List<BizOrder> totalOrders = orderMapper.selectList(totalQueryWrapper);



        return   DashBoard.builder()
                .DayTitckCount(dailyOrders.stream().mapToInt(BizOrder::getTicketCount).sum())
                .DayTotalPrice(dailyOrders.stream().map(BizOrder::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .WeekTitckCount(weeklyOrders.stream().mapToInt(BizOrder::getTicketCount).sum())
                .WeekTotalPrice(weeklyOrders.stream().map(BizOrder::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .TitckCount(totalOrders.stream().mapToInt(BizOrder::getTicketCount).sum())
                .TotalPrice(totalOrders.stream().map(BizOrder::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }

    @Override
    public List<SalesTrend> weekview(String id) {
        List<SalesTrend> list =  orderMapper.weekview(id);
        return list;
    }


}
