package com.snowy.client.modular.wxplan.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.client.modular.wxplan.entity.Plan;
import com.snowy.client.modular.wxplan.param.PlanPageParam;

public interface WxplanService extends IService<Plan>{
    Page<Plan> planPage(PlanPageParam planPageparam);

    Plan getNewPlan();
}
