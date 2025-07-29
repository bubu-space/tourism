
package com.snowy.dev.modular.job.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.job.entity.DevJob;
import com.snowy.dev.modular.job.param.*;

import java.util.List;

/**
 * 定时任务Service接口
 *
 * @author snowy
 *
 **/
public interface DevJobService extends IService<DevJob> {

    /**
     * 获取定时任务分页
     *
     * @author snowy
     *
     */
    Page<DevJob> page(DevJobPageParam devJobPageParam);

    /**
     * 获取定时任务列表
     *
     * @author snowy
     *
     */
    List<DevJob> list(DevJobListParam devJobListParam);

    /**
     * 添加定时任务
     *
     * @author snowy
     *
     */
    void add(DevJobAddParam devJobAddParam);

    /**
     * 编辑定时任务
     *
     * @author snowy
     *
     */
    void edit(DevJobEditParam devJobEditParam);

    /**
     * 删除定时任务
     *
     * @author snowy
     *
     */
    void delete(List<DevJobIdParam> devJobIdParamList);

    /**
     * 获取定时任务详情
     *
     * @author snowy
     *
     */
    DevJob detail(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务详情
     *
     * @author snowy
     *
     */
    DevJob queryEntity(String id);

    /**
     * 停止定时任务
     *
     * @author snowy
     *
     **/
    void stopJob(DevJobIdParam devJobIdParam);

    /**
     * 运行定时任务
     *
     * @author snowy
     *
     **/
    void runJob(DevJobIdParam devJobIdParam);

    /**
     * 立即运行定时任务
     *
     * @author snowy
     *
     **/
    void runJobNow(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务类
     *
     * @author snowy
     *
     **/
    List<String> getActionClass();
}
