package com.snowy.client.modular.wxindex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy.biz.modular.spot.entity.Spot;
import com.snowy.client.modular.wxindex.result.WxIndexSpotActivite;

import java.util.List;

public interface IndexSpotMapper extends BaseMapper<Spot>{

    List<WxIndexSpotActivite> getSpotActivite(String title);


}
