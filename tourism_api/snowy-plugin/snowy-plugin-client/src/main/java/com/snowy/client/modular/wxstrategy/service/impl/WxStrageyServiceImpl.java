package com.snowy.client.modular.wxstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.snowy.biz.modular.strategy.entity.Strategy;
import com.snowy.biz.modular.strategy.mapper.StrategyMapper;
import com.snowy.client.modular.wxstrategy.param.WxStrategyPageParam;
import com.snowy.client.modular.wxstrategy.service.WxStrageyService;
import com.snowy.common.page.CommonPageRequest;

import java.util.Optional;

@Service
public class WxStrageyServiceImpl extends ServiceImpl<StrategyMapper, Strategy> implements WxStrageyService {
    @Override
    public Page<Strategy> page(WxStrategyPageParam strategyPageParam) {
        LambdaQueryWrapper<Strategy> QueryWrapper = new LambdaQueryWrapper<>();
        Optional.ofNullable(strategyPageParam.getTitle()).ifPresent(title -> QueryWrapper.like(Strategy::getTitle, title));
        Optional.ofNullable(strategyPageParam.getCategory()).ifPresent(category -> QueryWrapper.eq(Strategy::getCategory, category));
        Optional.ofNullable(strategyPageParam.getUserId()).ifPresent(userId -> QueryWrapper.eq(Strategy::getUserId, userId));
        QueryWrapper.orderByDesc(Strategy::getCreateTime);
        return this.page(CommonPageRequest.defaultPage(), QueryWrapper);
    }

    @Override
    public Strategy getById(String id) {
        QueryWrapper<Strategy>QueryWrapper = new QueryWrapper<>();
        QueryWrapper.lambda().eq(Strategy::getId, id);
        Strategy strategy = this.getOne(QueryWrapper);
        strategy.setWatchCount(strategy.getWatchCount() + 1);
        this.updateById(strategy);
        return strategy;
    }

    @Override
    public boolean like(Long id) {
        QueryWrapper<Strategy>QueryWrapper = new QueryWrapper<>();
        QueryWrapper.lambda().eq(Strategy::getId, id);
        Strategy strategy = this.getOne(QueryWrapper);
        strategy.setLikeCount(strategy.getLikeCount() + 1);
        return this.updateById(strategy);
    }

    @Override
    public void cancelLike(Long id) {
        QueryWrapper<Strategy>QueryWrapper = new QueryWrapper<>();
        QueryWrapper.lambda().eq(Strategy::getId, id);
        Strategy strategy = this.getOne(QueryWrapper);
        strategy.setLikeCount(strategy.getLikeCount() - 1);
        this.updateById(strategy);
    }

}
