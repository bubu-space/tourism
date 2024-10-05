
package vip.xiaonuo.dev.modular.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.config.entity.DevConfig;
import vip.xiaonuo.dev.modular.config.param.*;

import java.util.List;

/**
 * 配置Service接口
 *
 * @author gtc
 *
 **/
public interface DevConfigService extends IService<DevConfig> {

    /**
     * 根据键获取值
     *
     * @author gtc
     *
     **/
    String getValueByKey(String key);

    /**
     * 获取配置分页
     *
     * @author gtc
     *
     */
    Page<DevConfig> page(DevConfigPageParam devConfigPageParam);

    /**
     * 获取基础配置列表
     *
     * @author gtc
     *
     */
    List<DevConfig> sysBaseList();

    /**
     * 获取配置列表
     *
     * @author gtc
     *
     */
    List<DevConfig> list(DevConfigListParam devConfigListParam);

    /**
     * 添加配置
     *
     * @author gtc
     *
     */
    void add(DevConfigAddParam devConfigAddParam);

    /**
     * 编辑配置
     *
     * @author gtc
     *
     */
    void edit(DevConfigEditParam devConfigEditParam);

    /**
     * 删除配置
     *
     * @author gtc
     *
     */
    void delete(List<DevConfigIdParam> devConfigIdParamList);

    /**
     * 获取配置详情
     *
     * @author gtc
     *
     */
    DevConfig detail(DevConfigIdParam devConfigIdParam);

    /**
     * 获取配置详情
     *
     * @author gtc
     *
     */
    DevConfig queryEntity(String id);

    /**
     * 配置批量更新
     *
     * @author gtc
     *
     **/
    void editBatch(List<DevConfigBatchParam> devConfigBatchParamList);
}
