
package com.snowy.dev.modular.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.log.entity.DevLog;
import com.snowy.dev.modular.log.param.DevLogDeleteParam;
import com.snowy.dev.modular.log.param.DevLogIdParam;
import com.snowy.dev.modular.log.param.DevLogPageParam;
import com.snowy.dev.modular.log.result.DevLogOpBarChartDataResult;
import com.snowy.dev.modular.log.result.DevLogOpPieChartDataResult;
import com.snowy.dev.modular.log.result.DevLogVisLineChartDataResult;
import com.snowy.dev.modular.log.result.DevLogVisPieChartDataResult;

import java.util.List;

/**
 * 日志Service接口
 *
 * @author snowy
 *
 */
public interface DevLogService extends IService<DevLog> {

    /**
     * 获取日志分页
     *
     * @author snowy
     *
     */
    Page<DevLog> page(DevLogPageParam devLogPageParam);

    /**
     * 获取单条日志详情
     */
    DevLog detail(DevLogIdParam devLogIdParam);

    /**
     * 清空日志
     *
     * @author snowy
     *
     */
    void delete(DevLogDeleteParam devLogDeleteParam);

    /**
     * 获取访问日志折线图数据
     *
     * @author snowy
     *
     */
    List<DevLogVisLineChartDataResult> visLogLineChartData();

    /**
     * 获取访问日志饼状图数据
     *
     * @author snowy
     *
     */
    List<DevLogVisPieChartDataResult> visLogPieChartData();

    /**
     * 获取操作日志柱状图数据
     *
     * @author snowy
     *
     */
    List<DevLogOpBarChartDataResult> opLogBarChartData();

    /**
     * 获取操作日志饼状图数据
     *
     * @author snowy
     *
     */
    List<DevLogOpPieChartDataResult> opLogPieChartData();
}
