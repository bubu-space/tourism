
package com.snowy.biz.modular.strategy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.strategy.entity.Strategy;
import com.snowy.biz.modular.strategy.param.StrategyAddParam;
import com.snowy.biz.modular.strategy.param.StrategyEditParam;
import com.snowy.biz.modular.strategy.param.StrategyIdParam;
import com.snowy.biz.modular.strategy.param.StrategyPageParam;

import java.util.List;

/**
 * 旅游攻略表：由用户发表Service接口
 *
 * @author snowy
 *
 **/
public interface StrategyService extends IService<Strategy> {

    /**
     * 获取旅游攻略表：由用户发表分页
     *
     * @author snowy
     *
     */
    Page<Strategy> page(StrategyPageParam strategyPageParam);

    /**
     * 添加旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    void add(StrategyAddParam strategyAddParam);

    void addForC(StrategyAddParam strategyAddParam);

    /**
     * 编辑旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    void edit(StrategyEditParam strategyEditParam);

    /**
     * 删除旅游攻略表：由用户发表
     *
     * @author snowy
     *
     */
    void delete(List<StrategyIdParam> strategyIdParamList);

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author snowy
     *
     */
    Strategy detail(StrategyIdParam strategyIdParam);

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author snowy
     *
     **/
    Strategy queryEntity(String id);

    List<Strategy> getRedStrategy();


}
