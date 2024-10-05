
package vip.xiaonuo.biz.modular.spot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.biz.modular.spot.mapper.SpotMapper;
import vip.xiaonuo.biz.modular.spot.param.SpotAddParam;
import vip.xiaonuo.biz.modular.spot.param.SpotEditParam;
import vip.xiaonuo.biz.modular.spot.param.SpotIdParam;
import vip.xiaonuo.biz.modular.spot.param.SpotPageParam;
import vip.xiaonuo.biz.modular.spot.result.Namelist;
import vip.xiaonuo.biz.modular.spot.service.SpotService;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

/**
 * 景点表Service接口实现类
 *
 * @author gtc
 *
 **/
@Service
public class SpotServiceImpl extends ServiceImpl<SpotMapper, Spot> implements SpotService {

    @Autowired
    private SpotMapper spotMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public static final String GEO_KEY = "spot:locations";



//    @PostConstruct
//    public void syncSpotsWithRedis() {
//        List<Spot> spots = this.list();
//        spots.forEach(spot -> {
//            Long count = stringRedisTemplate.opsForGeo().geoAdd(GEO_KEY, new Point(spot.getLongitude(),spot.getLatitude()), spot.getId());
//            if (count == null || count == 0) {
//                System.out.println("Spot already exists in Redis or failed to add: " + spot.getId());
//            }
//        });
//    }

    @Override
    public Page<Spot> page(SpotPageParam spotPageParam) {
        QueryWrapper<Spot> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(spotPageParam.getSpotName())) {
            queryWrapper.lambda().like(Spot::getSpotName, spotPageParam.getSpotName());
        }
        if(ObjectUtil.isNotEmpty(spotPageParam.getLocation())) {
            queryWrapper.lambda().eq(Spot::getAddress, spotPageParam.getLocation());
        }
        if(ObjectUtil.isAllNotEmpty(spotPageParam.getSortField(), spotPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(spotPageParam.getSortOrder());
            queryWrapper.orderBy(true, spotPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(spotPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Spot::getId);
        }
        Page<Spot> page = this.page(CommonPageRequest.defaultPage(), queryWrapper);
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SpotAddParam spotAddParam) {
        Spot spot = BeanUtil.toBean(spotAddParam, Spot.class);
//        stringRedisTemplate.opsForGeo().add(GEO_KEY, new Point(spot.getLongitude(),spot.getLatitude()), spot.getId());
        this.save(spot);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SpotEditParam spotEditParam) {
        Spot spot = this.queryEntity(spotEditParam.getId());
        BeanUtil.copyProperties(spotEditParam, spot);
//        stringRedisTemplate.opsForGeo().add(GEO_KEY, new Point(spot.getLongitude(),spot.getLatitude()), spot.getId());
        this.updateById(spot);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SpotIdParam> spotIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(spotIdParamList, SpotIdParam::getId));
    }

    @Override
    public Spot detail(SpotIdParam spotIdParam) {
        return this.queryEntity(spotIdParam.getId());
    }

    @Override
    public Spot queryEntity(String id) {
        Spot spot = this.getById(id);
        if(ObjectUtil.isEmpty(spot)) {
            throw new CommonException("景点表不存在，id值为：{}", id);
        }
        return spot;
    }
    /*获取首页热门景点*/
    @Override
    public List<Spot> getRedSpot() {
        LambdaQueryWrapper<Spot> spotLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spotLambdaQueryWrapper.orderByDesc(Spot::getRate).last("limit 3");
        return this.list(spotLambdaQueryWrapper);
    }

    @Override
    public List<Namelist> namelist() {
        LambdaQueryWrapper<Spot> spotLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spotLambdaQueryWrapper.select(Spot::getSpotName,Spot::getId,Spot::getCover,Spot::getRate,Spot::getStartLevel);
        List<Spot> list = this.list(spotLambdaQueryWrapper);
        return BeanUtil.copyToList(list, Namelist.class);
    }



    @Override
    public void updateSpotScoreInDatabase(String spotId, double newSpotScore) {
        Spot spot = this.getById(spotId);
        spot.setRate(BigDecimal.valueOf(newSpotScore));
        this.updateById(spot);
    }

    @Override
    public Spot getOrderByspotName(String orderId) {
        return spotMapper.getOrderByspotName(orderId);
    }

    @Override
    public List<Spot> spotBook(String spotname) {
        List<Spot> spots = spotMapper.spotBook(spotname);
        return spots;
    }

}
