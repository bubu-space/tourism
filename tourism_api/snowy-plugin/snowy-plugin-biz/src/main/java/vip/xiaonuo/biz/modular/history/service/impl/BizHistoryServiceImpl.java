package vip.xiaonuo.biz.modular.history.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.history.entity.BizHistory;
import vip.xiaonuo.biz.modular.history.mapper.BizHistoryMapper;
import vip.xiaonuo.biz.modular.history.param.BizHistoryAddParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryEditParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryIdParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryPageParam;
import vip.xiaonuo.biz.modular.history.service.BizHistoryService;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BizHistoryServiceImpl extends ServiceImpl<BizHistoryMapper, BizHistory> implements BizHistoryService {


    @Override
    public BizHistory queryEntity(String id) {
        BizHistory history = this.getById(id);
        if (ObjectUtil.isNull(history)){
            throw new CommonException("不存在，id值为{}",id);
        }
        return history;
    }

    @Override
    public BizHistory detail(BizHistoryIdParam idParam) {
        return this.queryEntity(idParam.getId());
    }

    @Override
    public Page<BizHistory> page(BizHistoryPageParam param) {
        LambdaQueryWrapper<BizHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotEmpty(param.getSpotId()),BizHistory::getSpotId,param.getSpotId());
        wrapper.like(ObjectUtil.isNotEmpty(param.getTitle()),BizHistory::getTitle,param.getTitle());
        wrapper.orderByAsc(BizHistory::getTracingTime);
        return this.page(CommonPageRequest.defaultPage(),wrapper);
    }

    @Override
    public void delete(List<BizHistoryIdParam> idParamList) {
        this.removeBatchByIds(idParamList.stream().map(BizHistoryIdParam::getId).collect(Collectors.toList()));
    }

    @Override
    public void edit(BizHistoryEditParam editParam) {
        BizHistory history = BeanUtil.copyProperties(editParam, BizHistory.class);
        this.updateById(history);
    }

    @Override
    public void add(BizHistoryAddParam addParam) {
        BizHistory history = BeanUtil.copyProperties(addParam, BizHistory.class);
        this.save(history);
    }
}
