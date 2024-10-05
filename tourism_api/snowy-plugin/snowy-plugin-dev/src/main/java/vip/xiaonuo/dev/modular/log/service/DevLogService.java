
package vip.xiaonuo.dev.modular.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.log.entity.DevLog;
import vip.xiaonuo.dev.modular.log.param.DevLogDeleteParam;
import vip.xiaonuo.dev.modular.log.param.DevLogIdParam;
import vip.xiaonuo.dev.modular.log.param.DevLogPageParam;
import vip.xiaonuo.dev.modular.log.result.DevLogOpBarChartDataResult;
import vip.xiaonuo.dev.modular.log.result.DevLogOpPieChartDataResult;
import vip.xiaonuo.dev.modular.log.result.DevLogVisLineChartDataResult;
import vip.xiaonuo.dev.modular.log.result.DevLogVisPieChartDataResult;

import java.util.List;

/**
 * 日志Service接口
 *
 * @author gtc
 *
 */
public interface DevLogService extends IService<DevLog> {

    /**
     * 获取日志分页
     *
     * @author gtc
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
     * @author gtc
     *
     */
    void delete(DevLogDeleteParam devLogDeleteParam);

    /**
     * 获取访问日志折线图数据
     *
     * @author gtc
     *
     */
    List<DevLogVisLineChartDataResult> visLogLineChartData();

    /**
     * 获取访问日志饼状图数据
     *
     * @author gtc
     *
     */
    List<DevLogVisPieChartDataResult> visLogPieChartData();

    /**
     * 获取操作日志柱状图数据
     *
     * @author gtc
     *
     */
    List<DevLogOpBarChartDataResult> opLogBarChartData();

    /**
     * 获取操作日志饼状图数据
     *
     * @author gtc
     *
     */
    List<DevLogOpPieChartDataResult> opLogPieChartData();
}
