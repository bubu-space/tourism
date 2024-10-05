package vip.xiaonuo.client.modular.wxindex.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.client.modular.wxindex.result.WxIndexSpotActivite;

import java.util.List;

public interface IndexSpotMapper extends BaseMapper<Spot>{

    List<WxIndexSpotActivite> getSpotActivite(String title);


}
