
package com.snowy.sys.modular.resource.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.snowy.sys.modular.resource.entity.SysMenu;

/**
 * 菜单Mapper接口
 *
 * @author snowy
 *
 **/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 删除数据并忽略插件（逻辑删除、租户拼接）
     *
     * @author snowy
     *
     */
    @InterceptorIgnore(tenantLine = "true")
    void deleteIgnoreInterceptor(@Param("ew") LambdaQueryWrapper<SysMenu> lambdaQueryWrapper);
}
