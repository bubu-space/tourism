package com.snowy.client.modular.wxstrategy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import com.snowy.biz.modular.strategy.entity.Strategy;
import com.snowy.client.modular.wxstrategy.param.WxStrategyPageParam;

@Service
public interface WxStrageyService extends IService<Strategy> {
    Page<Strategy> page(WxStrategyPageParam strategyPageParam);

    Strategy getById(String id);

    boolean like(Long id);

    void cancelLike(Long id);

}
