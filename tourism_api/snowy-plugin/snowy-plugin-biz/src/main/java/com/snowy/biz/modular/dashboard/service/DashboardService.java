package com.snowy.biz.modular.dashboard.service;

import org.springframework.stereotype.Service;
import com.snowy.biz.modular.dashboard.param.WeekViewId;
import com.snowy.biz.modular.dashboard.result.DashBoard;
import com.snowy.biz.modular.dashboard.result.SalesTrend;

import java.util.List;

@Service
public interface DashboardService {
    DashBoard dashboard();

    List<SalesTrend> weekview(String id);
}
