
package vip.xiaonuo.biz.modular.activitie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import vip.xiaonuo.biz.modular.activitie.entity.BizActivitie;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitiePageParam;

/**
 * 景区/景点活动表Mapper接口
 *
 * @author gtc
 *
 **/
public interface BizActivitieMapper extends BaseMapper<BizActivitie> {
    IPage<BizActivitie> selectPageWithSpotName(Page<BizActivitie> page, @Param("params") BizActivitiePageParam params);

    BizActivitie getinfo(String id);
}
