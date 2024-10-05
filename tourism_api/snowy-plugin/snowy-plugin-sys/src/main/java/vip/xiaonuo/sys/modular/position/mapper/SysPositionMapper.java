
package vip.xiaonuo.sys.modular.position.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaonuo.sys.modular.position.entity.SysPosition;

/**
 * 职位Mapper接口
 *
 * @author gtc
 *
 **/
public interface SysPositionMapper extends BaseMapper<SysPosition> {

    /**
     * 删除数据并忽略插件（逻辑删除、租户拼接）
     *
     * @author gtc
     *
     */
    @InterceptorIgnore(tenantLine = "true")
    void deleteIgnoreInterceptor(@Param("ew") LambdaQueryWrapper<SysPosition> lambdaQueryWrapper);
}
