
package vip.xiaonuo.biz.modular.activitie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.activitie.entity.BizActivitie;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitieAddParam;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitieEditParam;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitieIdParam;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitiePageParam;

import java.util.List;

/**
 * 景区/景点活动表Service接口
 *
 * @author gtc
 *
 **/
public interface BizActivitieService extends IService<BizActivitie> {

    /**
     * 获取景区/景点活动表分页
     *
     * @author gtc
     *
     */
    Page<BizActivitie> page(BizActivitiePageParam bizActivitiePageParam);

    /**
     * 添加景区/景点活动表
     *
     * @author gtc
     *
     */
    void add(BizActivitieAddParam bizActivitieAddParam);

    /**
     * 编辑景区/景点活动表
     *
     * @author gtc
     *
     */
    void edit(BizActivitieEditParam bizActivitieEditParam);

    /**
     * 删除景区/景点活动表
     *
     * @author gtc
     *
     */
    void delete(List<BizActivitieIdParam> bizActivitieIdParamList);

    /**
     * 获取景区/景点活动表详情
     *
     * @author gtc
     *
     */
    BizActivitie detail(BizActivitieIdParam bizActivitieIdParam);

    /**
     * 获取景区/景点活动表详情
     *
     * @author gtc
     *
     **/
    BizActivitie queryEntity(String id);
}
