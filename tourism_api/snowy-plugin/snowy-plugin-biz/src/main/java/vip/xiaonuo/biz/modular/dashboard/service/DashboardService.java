package vip.xiaonuo.biz.modular.dashboard.service;

import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.dashboard.param.WeekViewId;
import vip.xiaonuo.biz.modular.dashboard.result.DashBoard;
import vip.xiaonuo.biz.modular.dashboard.result.SalesTrend;

import java.util.List;

@Service
public interface DashboardService {
    DashBoard dashboard();

    List<SalesTrend> weekview(String id);
}
