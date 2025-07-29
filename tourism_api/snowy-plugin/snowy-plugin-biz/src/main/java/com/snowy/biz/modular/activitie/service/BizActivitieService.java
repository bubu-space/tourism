
package com.snowy.biz.modular.activitie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.activitie.entity.BizActivitie;
import com.snowy.biz.modular.activitie.param.BizActivitieAddParam;
import com.snowy.biz.modular.activitie.param.BizActivitieEditParam;
import com.snowy.biz.modular.activitie.param.BizActivitieIdParam;
import com.snowy.biz.modular.activitie.param.BizActivitiePageParam;

import java.util.List;

/**
 * 景区/景点活动表Service接口
 *
 * @author snowy
 *
 **/
public interface BizActivitieService extends IService<BizActivitie> {

    /**
     * 获取景区/景点活动表分页
     *
     * @author snowy
     *
     */
    Page<BizActivitie> page(BizActivitiePageParam bizActivitiePageParam);

    /**
     * 添加景区/景点活动表
     *
     * @author snowy
     *
     */
    void add(BizActivitieAddParam bizActivitieAddParam);

    /**
     * 编辑景区/景点活动表
     *
     * @author snowy
     *
     */
    void edit(BizActivitieEditParam bizActivitieEditParam);

    /**
     * 删除景区/景点活动表
     *
     * @author snowy
     *
     */
    void delete(List<BizActivitieIdParam> bizActivitieIdParamList);

    /**
     * 获取景区/景点活动表详情
     *
     * @author snowy
     *
     */
    BizActivitie detail(BizActivitieIdParam bizActivitieIdParam);

    /**
     * 获取景区/景点活动表详情
     *
     * @author snowy
     *
     **/
    BizActivitie queryEntity(String id);
}
