
package com.snowy.biz.modular.bizrecommend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.bizrecommend.entity.BizRecommend;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendAddParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendEditParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendIdParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendPageParam;

import java.util.List;

/**
 * 住/礼/食推荐表Service接口
 *
 * @author snowy
 *
 **/
public interface BizRecommendService extends IService<BizRecommend> {

    /**
     * 获取住/礼/食推荐表分页
     *
     * @author snowy
     *
     */
    Page<BizRecommend> page(BizRecommendPageParam bizRecommendPageParam);

    /**
     * 添加住/礼/食推荐表
     *
     * @author snowy
     *
     */
    void add(BizRecommendAddParam bizRecommendAddParam);

    /**
     * 编辑住/礼/食推荐表
     *
     * @author snowy
     *
     */
    void edit(BizRecommendEditParam bizRecommendEditParam);

    /**
     * 删除住/礼/食推荐表
     *
     * @author snowy
     *
     */
    void delete(List<BizRecommendIdParam> bizRecommendIdParamList);

    /**
     * 获取住/礼/食推荐表详情
     *
     * @author snowy
     *
     */
    BizRecommend detail(BizRecommendIdParam bizRecommendIdParam);

    /**
     * 获取住/礼/食推荐表详情
     *
     * @author snowy
     *
     **/
    BizRecommend queryEntity(String id);
}
