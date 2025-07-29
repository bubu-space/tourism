
package com.snowy.dev.modular.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.config.entity.DevConfig;
import com.snowy.dev.modular.config.param.*;

import java.util.List;

/**
 * 配置Service接口
 *
 * @author snowy
 *
 **/
public interface DevConfigService extends IService<DevConfig> {

    /**
     * 根据键获取值
     *
     * @author snowy
     *
     **/
    String getValueByKey(String key);

    /**
     * 获取配置分页
     *
     * @author snowy
     *
     */
    Page<DevConfig> page(DevConfigPageParam devConfigPageParam);

    /**
     * 获取基础配置列表
     *
     * @author snowy
     *
     */
    List<DevConfig> sysBaseList();

    /**
     * 获取配置列表
     *
     * @author snowy
     *
     */
    List<DevConfig> list(DevConfigListParam devConfigListParam);

    /**
     * 添加配置
     *
     * @author snowy
     *
     */
    void add(DevConfigAddParam devConfigAddParam);

    /**
     * 编辑配置
     *
     * @author snowy
     *
     */
    void edit(DevConfigEditParam devConfigEditParam);

    /**
     * 删除配置
     *
     * @author snowy
     *
     */
    void delete(List<DevConfigIdParam> devConfigIdParamList);

    /**
     * 获取配置详情
     *
     * @author snowy
     *
     */
    DevConfig detail(DevConfigIdParam devConfigIdParam);

    /**
     * 获取配置详情
     *
     * @author snowy
     *
     */
    DevConfig queryEntity(String id);

    /**
     * 配置批量更新
     *
     * @author snowy
     *
     **/
    void editBatch(List<DevConfigBatchParam> devConfigBatchParamList);
}
