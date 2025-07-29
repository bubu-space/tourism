
package com.snowy.biz.modular.bizrecommend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snowy.common.enums.CommonSortOrderEnum;
import com.snowy.common.exception.CommonException;
import com.snowy.common.page.CommonPageRequest;
import com.snowy.biz.modular.bizrecommend.entity.BizRecommend;
import com.snowy.biz.modular.bizrecommend.mapper.BizRecommendMapper;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendAddParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendEditParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendIdParam;
import com.snowy.biz.modular.bizrecommend.param.BizRecommendPageParam;
import com.snowy.biz.modular.bizrecommend.service.BizRecommendService;

import java.util.List;

/**
 * 住/礼/食推荐表Service接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class BizRecommendServiceImpl extends ServiceImpl<BizRecommendMapper, BizRecommend> implements BizRecommendService {

    @Override
    public Page<BizRecommend> page(BizRecommendPageParam bizRecommendPageParam) {
        QueryWrapper<BizRecommend> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(bizRecommendPageParam.getCategory())) {
            queryWrapper.lambda().eq(BizRecommend::getCategory, bizRecommendPageParam.getCategory());
        }
        if(ObjectUtil.isNotEmpty(bizRecommendPageParam.getTitle())) {
            queryWrapper.lambda().like(BizRecommend::getTitle, bizRecommendPageParam.getTitle());
        }
        else {
            queryWrapper.lambda().orderByAsc(BizRecommend::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizRecommendAddParam bizRecommendAddParam) {
        BizRecommend bizRecommend = BeanUtil.copyProperties(bizRecommendAddParam, BizRecommend.class);
        this.save(bizRecommend);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizRecommendEditParam bizRecommendEditParam) {
        BizRecommend bizRecommend = this.queryEntity(bizRecommendEditParam.getId());
        BeanUtil.copyProperties(bizRecommendEditParam, bizRecommend);
        this.updateById(bizRecommend);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizRecommendIdParam> bizRecommendIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizRecommendIdParamList, BizRecommendIdParam::getId));
    }

    @Override
    public BizRecommend detail(BizRecommendIdParam bizRecommendIdParam) {
        return this.queryEntity(bizRecommendIdParam.getId());
    }

    @Override
    public BizRecommend queryEntity(String id) {
        BizRecommend bizRecommend = this.getById(id);
        if(ObjectUtil.isEmpty(bizRecommend)) {
            throw new CommonException("该推荐未找到，id值为：{}", id);
        }
        return bizRecommend;
    }
}
