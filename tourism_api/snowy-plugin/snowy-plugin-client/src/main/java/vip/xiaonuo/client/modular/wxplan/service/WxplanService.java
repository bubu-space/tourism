package vip.xiaonuo.client.modular.wxplan.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.client.modular.wxplan.entity.Plan;
import vip.xiaonuo.client.modular.wxplan.param.PlanPageParam;

public interface WxplanService extends IService<Plan>{
    Page<Plan> planPage(PlanPageParam planPageparam);

    Plan getNewPlan();
}
