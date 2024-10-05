package vip.xiaonuo.client.modular.wxplan.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.xiaonuo.auth.core.util.StpClientUtil;
import vip.xiaonuo.client.modular.wxplan.entity.Plan;
import vip.xiaonuo.client.modular.wxplan.mapper.WxPlanMapper;
import vip.xiaonuo.client.modular.wxplan.param.PlanPageParam;
import vip.xiaonuo.client.modular.wxplan.service.WxplanService;
import vip.xiaonuo.client.modular.wxuser.entity.ClientUser;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.page.CommonPageRequest;

import java.util.List;
import java.util.Optional;

@Service
public class WxplanServiceImpl extends ServiceImpl<WxPlanMapper,Plan> implements WxplanService {
    /**
     * 分页查询计划
     */
    @Override
    public Page<Plan> planPage(PlanPageParam planPageparam) {
        LambdaQueryWrapper<Plan> planLambdaQueryWrapper = new LambdaQueryWrapper<>();
        String loginIdAsString = StpClientUtil.getLoginIdAsString();
        planLambdaQueryWrapper.eq(Plan::getUserId, loginIdAsString);
        if (CommonSortOrderEnum.ASC.getValue().equals(planPageparam.getSortOrder())) {
            planLambdaQueryWrapper.orderByAsc(Plan::getCreateTime);
        } else if (CommonSortOrderEnum.DESC.getValue().equals(planPageparam.getSortOrder())) {
            planLambdaQueryWrapper.orderByDesc(Plan::getCreateTime);
        }
        if(StrUtil.isNotEmpty(planPageparam.getStatus())) {
            planLambdaQueryWrapper.like(Plan::getStatus, planPageparam.getStatus());
        }

        return this.page(CommonPageRequest.defaultPage(), planLambdaQueryWrapper);
    }

    /**
     * 获取最新的计划
     */

    @Override
    public Plan getNewPlan() {
        ClientUser clientUser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
        LambdaQueryWrapper<Plan> planLambdaQueryWrapper = new LambdaQueryWrapper<>();
        planLambdaQueryWrapper.eq(Plan::getUserId, clientUser.getId());
        planLambdaQueryWrapper.orderByDesc(Plan::getCreateTime);
        if(this.list(planLambdaQueryWrapper).isEmpty()) {
            return null;
        }else {
            return this.list(planLambdaQueryWrapper).get(0);
        }
    }
}
