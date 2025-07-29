
package com.snowy.sys.modular.resource.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.snowy.sys.modular.resource.entity.SysModule;

/**
 * 模块Mapper接口
 *
 * @author snowy
 *
 **/
public interface SysModuleMapper extends BaseMapper<SysModule> {

    /**
     * 删除数据并忽略插件（逻辑删除、租户拼接）
     *
     * @author snowy
     *
     */
    @InterceptorIgnore(tenantLine = "true")
    void deleteIgnoreInterceptor(@Param("ew") LambdaQueryWrapper<SysModule> lambdaQueryWrapper);

}
