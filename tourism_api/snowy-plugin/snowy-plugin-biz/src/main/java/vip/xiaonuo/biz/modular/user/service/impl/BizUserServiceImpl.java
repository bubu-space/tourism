
package vip.xiaonuo.biz.modular.user.service.impl;

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
import vip.xiaonuo.biz.modular.user.entity.BizUser;
import vip.xiaonuo.biz.modular.user.mapper.BizUserMapper;
import vip.xiaonuo.biz.modular.user.param.BizUserAddParam;
import vip.xiaonuo.biz.modular.user.param.BizUserEditParam;
import vip.xiaonuo.biz.modular.user.param.BizUserIdParam;
import vip.xiaonuo.biz.modular.user.param.BizUserPageParam;
import vip.xiaonuo.biz.modular.user.service.BizUserService;

import java.util.List;

/**
 * 用户表Service接口实现类
 *
 * @author gtc
 *
 **/
@Service
public class BizUserServiceImpl extends ServiceImpl<BizUserMapper, BizUser> implements BizUserService {

    @Override
    public Page<BizUser> page(BizUserPageParam bizUserPageParam) {
        QueryWrapper<BizUser> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(bizUserPageParam.getName())) {
            queryWrapper.lambda().like(BizUser::getName, bizUserPageParam.getName());
        }
        if(ObjectUtil.isNotEmpty(bizUserPageParam.getPhone())) {
            queryWrapper.lambda().eq(BizUser::getPhone, bizUserPageParam.getPhone());
        }
        if(ObjectUtil.isNotEmpty(bizUserPageParam.getStatus())) {
            queryWrapper.lambda().eq(BizUser::getStatus, bizUserPageParam.getStatus());
        }
        if(ObjectUtil.isAllNotEmpty(bizUserPageParam.getSortField(), bizUserPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizUserPageParam.getSortOrder());
            queryWrapper.orderBy(true, bizUserPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizUserPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizUser::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizUserAddParam bizUserAddParam) {
        BizUser bizUser = BeanUtil.toBean(bizUserAddParam, BizUser.class);
        this.save(bizUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizUserEditParam bizUserEditParam) {
        BizUser bizUser = this.queryEntity(bizUserEditParam.getId());
        BeanUtil.copyProperties(bizUserEditParam, bizUser);
        this.updateById(bizUser);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizUserIdParam> bizUserIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizUserIdParamList, BizUserIdParam::getId));
    }

    @Override
    public BizUser detail(BizUserIdParam bizUserIdParam) {
        return this.queryEntity(bizUserIdParam.getId());
    }

    @Override
    public BizUser queryEntity(String id) {
        BizUser bizUser = this.getById(id);
        if(ObjectUtil.isEmpty(bizUser)) {
            throw new CommonException("用户表不存在，id值为：{}", id);
        }
        return bizUser;
    }
}
