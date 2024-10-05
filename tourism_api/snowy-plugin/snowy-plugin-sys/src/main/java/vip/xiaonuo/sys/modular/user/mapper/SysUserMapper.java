
package vip.xiaonuo.sys.modular.user.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import vip.xiaonuo.sys.modular.user.entity.SysUser;

/**
 * 用户Mapper接口
 *
 * @author gtc
 *
 **/
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 删除数据并忽略插件（逻辑删除、租户拼接）
     *
     * @author gtc
     *
     */
    @InterceptorIgnore(tenantLine = "true")
    void deleteIgnoreInterceptor(@Param("ew") LambdaQueryWrapper<SysUser> lambdaQueryWrapper);
}