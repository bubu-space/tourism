
package vip.xiaonuo.biz.modular.spot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.biz.modular.spot.param.SpotAddParam;
import vip.xiaonuo.biz.modular.spot.param.SpotEditParam;
import vip.xiaonuo.biz.modular.spot.param.SpotIdParam;
import vip.xiaonuo.biz.modular.spot.param.SpotPageParam;
import vip.xiaonuo.biz.modular.spot.result.Namelist;

import java.util.List;

/**
 * 景点表Service接口
 *
 * @author gtc
 * 
 **/
public interface SpotService extends IService<Spot> {

    /**
     * 获取景点表分页
     *
     * @author gtc
     * 
     */
    Page<Spot> page(SpotPageParam spotPageParam);

    /**
     * 添加景点表
     *
     * @author gtc
     * 
     */
    void add(SpotAddParam spotAddParam);

    /**
     * 编辑景点表
     *
     * @author gtc
     * 
     */
    void edit(SpotEditParam spotEditParam);

    /**
     * 删除景点表
     *
     * @author gtc
     * 
     */
    void delete(List<SpotIdParam> spotIdParamList);

    /**
     * 获取景点表详情
     *
     * @author gtc
     * 
     */
    Spot detail(SpotIdParam spotIdParam);

    /**
     * 获取景点表详情
     *
     * @author gtc
     * 
     **/
    Spot queryEntity(String id);

    List<Spot> getRedSpot();

    List<Namelist> namelist();

    void updateSpotScoreInDatabase(String spotId, double newSpotScore);


    Spot getOrderByspotName(String orderId);

    List<Spot> spotBook(String spotname);
}
