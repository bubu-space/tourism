
package com.snowy.biz.modular.bizheritage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.bizheritage.entity.BizHeritage;
import com.snowy.biz.modular.bizheritage.param.BizHeritageAddParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritageEditParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritageIdParam;
import com.snowy.biz.modular.bizheritage.param.BizHeritagePageParam;

import java.util.List;

/**
 * 历史遗产动态表Service接口
 *
 * @author snowy
 *
 **/
public interface BizHeritageService extends IService<BizHeritage> {

    /**
     * 获取历史遗产动态表分页
     *
     * @author snowy
     *
     */
    Page<BizHeritage> page(BizHeritagePageParam bizHeritagePageParam);

    /**
     * 添加历史遗产动态表
     *
     * @author snowy
     *
     */
    void add(BizHeritageAddParam bizHeritageAddParam);

    /**
     * 编辑历史遗产动态表
     *
     * @author snowy
     *
     */
    void edit(BizHeritageEditParam bizHeritageEditParam);

    /**
     * 删除历史遗产动态表
     *
     * @author snowy
     *
     */
    void delete(List<BizHeritageIdParam> bizHeritageIdParamList);

    /**
     * 获取历史遗产动态表详情
     *
     * @author snowy
     *
     */
    BizHeritage detail(BizHeritageIdParam bizHeritageIdParam);

    /**
     * 获取历史遗产动态表详情
     *
     * @author snowy
     *
     **/
    BizHeritage queryEntity(String id);
}
