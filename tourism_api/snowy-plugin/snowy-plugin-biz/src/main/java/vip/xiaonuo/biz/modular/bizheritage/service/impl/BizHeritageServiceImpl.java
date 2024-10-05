
package vip.xiaonuo.biz.modular.bizheritage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.bizheritage.entity.BizHeritage;
import vip.xiaonuo.biz.modular.bizheritage.mapper.BizHeritageMapper;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageAddParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageEditParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageIdParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritagePageParam;
import vip.xiaonuo.biz.modular.bizheritage.service.BizHeritageService;

import java.util.List;

/**
 * 历史遗产动态表Service接口实现类
 *
 * @author gtc
 *
 **/
@Service
public class BizHeritageServiceImpl extends ServiceImpl<BizHeritageMapper, BizHeritage> implements BizHeritageService {

    @Override
    public Page<BizHeritage> page(BizHeritagePageParam bizHeritagePageParam) {
        QueryWrapper<BizHeritage> queryWrapper = new QueryWrapper<>();

        if(ObjectUtil.isNotEmpty(bizHeritagePageParam.getTitle())) {
            queryWrapper.lambda().like(BizHeritage::getTitle, bizHeritagePageParam.getTitle());
        }
        if(ObjectUtil.isNotEmpty(bizHeritagePageParam.getCategory())) {
            queryWrapper.lambda().eq(BizHeritage::getCategory, bizHeritagePageParam.getCategory());
        }

        if(ObjectUtil.isAllNotEmpty(bizHeritagePageParam.getSortField(), bizHeritagePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizHeritagePageParam.getSortOrder());
            queryWrapper.orderBy(true, bizHeritagePageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizHeritagePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizHeritage::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizHeritageAddParam bizHeritageAddParam) {
        BizHeritage bizHeritage = BeanUtil.toBean(bizHeritageAddParam, BizHeritage.class);
        this.save(bizHeritage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizHeritageEditParam bizHeritageEditParam) {
        BizHeritage bizHeritage = this.queryEntity(bizHeritageEditParam.getId());
        BeanUtil.copyProperties(bizHeritageEditParam, bizHeritage);
        this.updateById(bizHeritage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizHeritageIdParam> bizHeritageIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizHeritageIdParamList, BizHeritageIdParam::getId));
    }

    @Override
    public BizHeritage detail(BizHeritageIdParam bizHeritageIdParam) {
        return this.queryEntity(bizHeritageIdParam.getId());
    }

    @Override
    public BizHeritage queryEntity(String id) {
        BizHeritage bizHeritage = this.getById(id);
        if(ObjectUtil.isEmpty(bizHeritage)) {
            throw new CommonException("历史遗产动态表不存在，id值为：{}", id);
        }
        return bizHeritage;
    }
}
