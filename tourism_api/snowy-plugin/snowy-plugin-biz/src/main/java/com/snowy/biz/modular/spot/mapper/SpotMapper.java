
package com.snowy.biz.modular.spot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snowy.biz.modular.spot.entity.Spot;

import java.util.List;

/**
 * 景点表Mapper接口
 *
 * @author snowy
 * 
 **/
public interface SpotMapper extends BaseMapper<Spot> {

    Spot getOrderByspotName(String orderId);

    List<Spot> spotBook(String spotname);
}
