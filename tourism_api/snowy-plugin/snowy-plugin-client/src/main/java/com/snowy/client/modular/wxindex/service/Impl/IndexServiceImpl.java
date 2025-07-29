package com.snowy.client.modular.wxindex.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.snowy.biz.modular.activitie.entity.BizActivitie;
import com.snowy.biz.modular.activitie.mapper.BizActivitieMapper;
import com.snowy.biz.modular.activitie.service.BizActivitieService;
import com.snowy.biz.modular.spot.mapper.SpotMapper;
import com.snowy.client.modular.wxindex.mapper.IndexSpotMapper;
import com.snowy.client.modular.wxindex.result.WxIndexSpotActivite;
import com.snowy.client.modular.wxindex.service.IndexService;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexSpotMapper indexSpotMapper;

    @Autowired
    private BizActivitieMapper bizActivitieMapper;



    @Override
    public List<WxIndexSpotActivite> getSpotActivite(String title) {
        List<WxIndexSpotActivite> list = indexSpotMapper.getSpotActivite(title);
        return list;
    }

}
