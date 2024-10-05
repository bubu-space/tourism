
package vip.xiaonuo.biz.modular.bizrecommend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.bizrecommend.entity.BizRecommend;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendAddParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendEditParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendIdParam;
import vip.xiaonuo.biz.modular.bizrecommend.param.BizRecommendPageParam;

import java.util.List;

/**
 * 住/礼/食推荐表Service接口
 *
 * @author gtc
 *
 **/
public interface BizRecommendService extends IService<BizRecommend> {

    /**
     * 获取住/礼/食推荐表分页
     *
     * @author gtc
     *
     */
    Page<BizRecommend> page(BizRecommendPageParam bizRecommendPageParam);

    /**
     * 添加住/礼/食推荐表
     *
     * @author gtc
     *
     */
    void add(BizRecommendAddParam bizRecommendAddParam);

    /**
     * 编辑住/礼/食推荐表
     *
     * @author gtc
     *
     */
    void edit(BizRecommendEditParam bizRecommendEditParam);

    /**
     * 删除住/礼/食推荐表
     *
     * @author gtc
     *
     */
    void delete(List<BizRecommendIdParam> bizRecommendIdParamList);

    /**
     * 获取住/礼/食推荐表详情
     *
     * @author gtc
     *
     */
    BizRecommend detail(BizRecommendIdParam bizRecommendIdParam);

    /**
     * 获取住/礼/食推荐表详情
     *
     * @author gtc
     *
     **/
    BizRecommend queryEntity(String id);
}
