
package com.snowy.biz.modular.strategy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.auth.core.util.StpClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snowy.common.enums.CommonSortOrderEnum;
import com.snowy.common.exception.CommonException;
import com.snowy.common.page.CommonPageRequest;
import com.snowy.biz.modular.strategy.entity.Strategy;
import com.snowy.biz.modular.strategy.mapper.StrategyMapper;
import com.snowy.biz.modular.strategy.param.StrategyAddParam;
import com.snowy.biz.modular.strategy.param.StrategyEditParam;
import com.snowy.biz.modular.strategy.param.StrategyIdParam;
import com.snowy.biz.modular.strategy.param.StrategyPageParam;
import com.snowy.biz.modular.strategy.service.StrategyService;

import java.util.List;

/**
 * 旅游攻略表：由用户发表Service接口实现类
 *
 * @author snowy
 * 
 **/
@Service
public class StrategyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements StrategyService {

    @Override
    public Page<Strategy> page(StrategyPageParam strategyPageParam) {
        QueryWrapper<Strategy> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(strategyPageParam.getCategory())) {
            queryWrapper.lambda().eq(Strategy::getCategory, strategyPageParam.getCategory());
        }
        if(ObjectUtil.isNotEmpty(strategyPageParam.getTitle())) {
            queryWrapper.lambda().like(Strategy::getTitle, strategyPageParam.getTitle());
        }
        if(ObjectUtil.isAllNotEmpty(strategyPageParam.getSortField(), strategyPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(strategyPageParam.getSortOrder());
            queryWrapper.orderBy(true, strategyPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(strategyPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Strategy::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addForC(StrategyAddParam strategyAddParam) {
        Strategy strategy = BeanUtil.toBean(strategyAddParam, Strategy.class);
        strategy.setWatchCount(0);
        strategy.setUserId(StpClientUtil.getLoginIdAsString());
        this.save(strategy);
    }

    @Override
    public void add(StrategyAddParam strategyAddParam) {
        Strategy strategy = BeanUtil.toBean(strategyAddParam, Strategy.class);
        strategy.setUserId(StpUtil.getLoginIdAsString());
        this.save(strategy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(StrategyEditParam strategyEditParam) {
        Strategy strategy = this.queryEntity(strategyEditParam.getId());
        BeanUtil.copyProperties(strategyEditParam, strategy);
        this.updateById(strategy);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<StrategyIdParam> strategyIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(strategyIdParamList, StrategyIdParam::getId));
    }

    @Override
    public Strategy detail(StrategyIdParam strategyIdParam) {
        return this.queryEntity(strategyIdParam.getId());
    }

    @Override
    public Strategy queryEntity(String id) {
        Strategy strategy = this.getById(id);
        if(ObjectUtil.isEmpty(strategy)) {
            throw new CommonException("旅游攻略表：id值为：{}", id);
        }
        return strategy;
    }

    /*获取首页热门攻略*/
    @Override
    public List<Strategy> getRedStrategy() {
        LambdaQueryWrapper<Strategy> strategyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        strategyLambdaQueryWrapper.orderByDesc(Strategy::getLikeCount)
                .last("limit 3");
        return this.list(strategyLambdaQueryWrapper);
    }
}
