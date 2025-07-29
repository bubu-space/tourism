
package com.snowy.biz.modular.activitie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snowy.common.exception.CommonException;
import com.snowy.common.page.CommonPageRequest;
import com.snowy.biz.modular.activitie.entity.BizActivitie;
import com.snowy.biz.modular.activitie.mapper.BizActivitieMapper;
import com.snowy.biz.modular.activitie.param.BizActivitieAddParam;
import com.snowy.biz.modular.activitie.param.BizActivitieEditParam;
import com.snowy.biz.modular.activitie.param.BizActivitieIdParam;
import com.snowy.biz.modular.activitie.param.BizActivitiePageParam;
import com.snowy.biz.modular.activitie.service.BizActivitieService;

import java.util.List;

/**
 * 景区/景点活动表Service接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class BizActivitieServiceImpl extends ServiceImpl<BizActivitieMapper, BizActivitie> implements BizActivitieService {
    @Autowired
    private BizActivitieMapper bizActivitieMapper;

    @Override
    public Page<BizActivitie> page(BizActivitiePageParam bizActivitiePageParam) {
//        QueryWrapper<BizActivitie> queryWrapper = new QueryWrapper<>();
//        if(ObjectUtil.isNotEmpty(bizActivitiePageParam.getSpotId())) {
//            queryWrapper.lambda().eq(BizActivitie::getSpotId, bizActivitiePageParam.getSpotId());
//        }
//        if(ObjectUtil.isNotEmpty(bizActivitiePageParam.getTitle())) {
//            queryWrapper.lambda().like(BizActivitie::getTitle, bizActivitiePageParam.getTitle());
//        }
//        if(ObjectUtil.isAllNotEmpty(bizActivitiePageParam.getSortField(), bizActivitiePageParam.getSortOrder())) {
//            CommonSortOrderEnum.validate(bizActivitiePageParam.getSortOrder());
//            queryWrapper.orderBy(true, bizActivitiePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
//                    StrUtil.toUnderlineCase(bizActivitiePageParam.getSortField()));
//        } else {
//            queryWrapper.lambda().orderByAsc(BizActivitie::getId);
//        }
//        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
        Page<BizActivitie> page = CommonPageRequest.defaultPage();
        IPage<BizActivitie> iPage = bizActivitieMapper.selectPageWithSpotName(page, bizActivitiePageParam);
        return new Page<BizActivitie>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal()).setRecords(iPage.getRecords());

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizActivitieAddParam bizActivitieAddParam) {
        BizActivitie bizActivitie = BeanUtil.toBean(bizActivitieAddParam, BizActivitie.class);
        bizActivitie.setViewCount(0);
        this.save(bizActivitie);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizActivitieEditParam bizActivitieEditParam) {
        BizActivitie bizActivitie = this.queryEntity(bizActivitieEditParam.getId());
        BeanUtil.copyProperties(bizActivitieEditParam, bizActivitie);
        this.updateById(bizActivitie);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizActivitieIdParam> bizActivitieIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizActivitieIdParamList, BizActivitieIdParam::getId));
    }

    @Override
    public BizActivitie detail(BizActivitieIdParam bizActivitieIdParam) {
        return this.queryEntity(bizActivitieIdParam.getId());
    }

    @Override
    public BizActivitie queryEntity(String id) {
        BizActivitie bizActivitie = bizActivitieMapper.getinfo(id);
        if(ObjectUtil.isEmpty(bizActivitie)) {
            throw new CommonException("景区/景点活动表不存在，id值为：{}", id);
        }
        return bizActivitie;
    }
}
