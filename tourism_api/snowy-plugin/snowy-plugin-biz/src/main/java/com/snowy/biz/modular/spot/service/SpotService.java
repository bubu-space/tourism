
package com.snowy.biz.modular.spot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.spot.entity.Spot;
import com.snowy.biz.modular.spot.param.SpotAddParam;
import com.snowy.biz.modular.spot.param.SpotEditParam;
import com.snowy.biz.modular.spot.param.SpotIdParam;
import com.snowy.biz.modular.spot.param.SpotPageParam;
import com.snowy.biz.modular.spot.result.Namelist;

import java.util.List;

/**
 * 景点表Service接口
 *
 * @author snowy
 * 
 **/
public interface SpotService extends IService<Spot> {

    /**
     * 获取景点表分页
     *
     * @author snowy
     * 
     */
    Page<Spot> page(SpotPageParam spotPageParam);

    /**
     * 添加景点表
     *
     * @author snowy
     * 
     */
    void add(SpotAddParam spotAddParam);

    /**
     * 编辑景点表
     *
     * @author snowy
     * 
     */
    void edit(SpotEditParam spotEditParam);

    /**
     * 删除景点表
     *
     * @author snowy
     * 
     */
    void delete(List<SpotIdParam> spotIdParamList);

    /**
     * 获取景点表详情
     *
     * @author snowy
     * 
     */
    Spot detail(SpotIdParam spotIdParam);

    /**
     * 获取景点表详情
     *
     * @author snowy
     * 
     **/
    Spot queryEntity(String id);

    List<Spot> getRedSpot();

    List<Namelist> namelist();

    void updateSpotScoreInDatabase(String spotId, double newSpotScore);


    Spot getOrderByspotName(String orderId);

    List<Spot> spotBook(String spotname);
}
