package vip.xiaonuo.client.modular.wxindex.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.activitie.entity.BizActivitie;
import vip.xiaonuo.biz.modular.activitie.mapper.BizActivitieMapper;
import vip.xiaonuo.biz.modular.activitie.service.BizActivitieService;
import vip.xiaonuo.biz.modular.spot.mapper.SpotMapper;
import vip.xiaonuo.client.modular.wxindex.mapper.IndexSpotMapper;
import vip.xiaonuo.client.modular.wxindex.result.WxIndexSpotActivite;
import vip.xiaonuo.client.modular.wxindex.service.IndexService;

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
