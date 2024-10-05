package vip.xiaonuo.biz.modular.history.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.history.entity.BizHistory;
import vip.xiaonuo.biz.modular.history.param.BizHistoryAddParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryEditParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryIdParam;
import vip.xiaonuo.biz.modular.history.param.BizHistoryPageParam;

import java.util.List;

public interface BizHistoryService extends IService<BizHistory> {

    BizHistory queryEntity(String id);

    BizHistory detail(BizHistoryIdParam idParam);

    Page<BizHistory> page(BizHistoryPageParam param);

    void delete(List<BizHistoryIdParam> idParamList);

    void edit(BizHistoryEditParam editParam);

    void add(BizHistoryAddParam addParam);
}
