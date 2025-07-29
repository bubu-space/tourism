
package com.snowy.biz.modular.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import com.snowy.biz.modular.dashboard.param.WeekViewId;
import com.snowy.biz.modular.dashboard.result.SalesTrend;
import com.snowy.biz.modular.order.entity.BizOrder;
import com.snowy.biz.modular.order.param.BizTicketOrderPageParam;

import java.util.List;

/**
 * 门票订单表Mapper接口
 *
 * @author snowy
 * 
 **/
@Mapper
public interface BizTicketOrderMapper extends BaseMapper<BizOrder> {
    List<SalesTrend> weekview(String id);

    Page<BizOrder> spotImagepage(Page<BizOrder> bizOrderPage, BizTicketOrderPageParam bizTicketOrderPageParam);
}
