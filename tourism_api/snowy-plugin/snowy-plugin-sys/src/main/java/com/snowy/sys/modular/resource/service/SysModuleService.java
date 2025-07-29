
package com.snowy.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.sys.modular.resource.entity.SysModule;
import com.snowy.sys.modular.resource.param.module.SysModuleAddParam;
import com.snowy.sys.modular.resource.param.module.SysModuleEditParam;
import com.snowy.sys.modular.resource.param.module.SysModuleIdParam;
import com.snowy.sys.modular.resource.param.module.SysModulePageParam;

import java.util.List;

/**
 * 模块Service接口
 *
 * @author snowy
 *
 **/
public interface SysModuleService extends IService<SysModule> {

    /**
     * 获取模块分页
     *
     * @author snowy
     *
     */
    Page<SysModule> page(SysModulePageParam sysModulePageParam);

    /**
     * 添加模块
     *
     * @author snowy
     *
     */
    void add(SysModuleAddParam sysModuleAddParam);

    /**
     * 编辑模块
     *
     * @author snowy
     *
     */
    void edit(SysModuleEditParam sysModuleEditParam);

    /**
     * 删除模块
     *
     * @author snowy
     *
     */
    void delete(List<SysModuleIdParam> sysModuleIdParamList);

    /**
     * 获取模块详情
     *
     * @author snowy
     *
     */
    SysModule detail(SysModuleIdParam sysModuleIdParam);

    /**
     * 获取模块详情
     *
     * @author snowy
     *
     */
    SysModule queryEntity(String id);
}
