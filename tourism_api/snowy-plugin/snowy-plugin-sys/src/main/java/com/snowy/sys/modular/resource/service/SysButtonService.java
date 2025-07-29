
package com.snowy.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.sys.modular.resource.entity.SysButton;
import com.snowy.sys.modular.resource.param.button.SysButtonAddParam;
import com.snowy.sys.modular.resource.param.button.SysButtonEditParam;
import com.snowy.sys.modular.resource.param.button.SysButtonIdParam;
import com.snowy.sys.modular.resource.param.button.SysButtonPageParam;

import java.util.List;

/**
 * 按钮Service接口
 *
 * @author snowy
 *
 **/
public interface SysButtonService extends IService<SysButton> {

    /**
     * 获取按钮分页
     *
     * @author snowy
     *
     */
    Page<SysButton> page(SysButtonPageParam sysButtonPageParam);

    /**
     * 添加按钮
     *
     * @author snowy
     *
     */
    void add(SysButtonAddParam sysButtonAddParam);

    /**
     * 代码生成按钮插入
     *
     * @author snowy
     *
     * @return java.lang.String
     **/
    void addForGenButton(String menuId, String className, String functionName);

    /**
     * 编辑按钮
     *
     * @author snowy
     *
     */
    void edit(SysButtonEditParam sysButtonEditParam);

    /**
     * 删除按钮
     *
     * @author snowy
     *
     */
    void delete(List<SysButtonIdParam> sysButtonIdParamList);

    /**
     * 获取按钮详情
     *
     * @author snowy
     *
     */
    SysButton detail(SysButtonIdParam sysButtonIdParam);

    /**
     * 获取按钮详情
     *
     * @author snowy
     *
     */
    SysButton queryEntity(String id);
}
