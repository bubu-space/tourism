
package vip.xiaonuo.dev.modular.job.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.job.entity.DevJob;
import vip.xiaonuo.dev.modular.job.param.*;

import java.util.List;

/**
 * 定时任务Service接口
 *
 * @author gtc
 *
 **/
public interface DevJobService extends IService<DevJob> {

    /**
     * 获取定时任务分页
     *
     * @author gtc
     *
     */
    Page<DevJob> page(DevJobPageParam devJobPageParam);

    /**
     * 获取定时任务列表
     *
     * @author gtc
     *
     */
    List<DevJob> list(DevJobListParam devJobListParam);

    /**
     * 添加定时任务
     *
     * @author gtc
     *
     */
    void add(DevJobAddParam devJobAddParam);

    /**
     * 编辑定时任务
     *
     * @author gtc
     *
     */
    void edit(DevJobEditParam devJobEditParam);

    /**
     * 删除定时任务
     *
     * @author gtc
     *
     */
    void delete(List<DevJobIdParam> devJobIdParamList);

    /**
     * 获取定时任务详情
     *
     * @author gtc
     *
     */
    DevJob detail(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务详情
     *
     * @author gtc
     *
     */
    DevJob queryEntity(String id);

    /**
     * 停止定时任务
     *
     * @author gtc
     *
     **/
    void stopJob(DevJobIdParam devJobIdParam);

    /**
     * 运行定时任务
     *
     * @author gtc
     *
     **/
    void runJob(DevJobIdParam devJobIdParam);

    /**
     * 立即运行定时任务
     *
     * @author gtc
     *
     **/
    void runJobNow(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务类
     *
     * @author gtc
     *
     **/
    List<String> getActionClass();
}
