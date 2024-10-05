
package vip.xiaonuo.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.resource.entity.SysModule;
import vip.xiaonuo.sys.modular.resource.param.module.SysModuleAddParam;
import vip.xiaonuo.sys.modular.resource.param.module.SysModuleEditParam;
import vip.xiaonuo.sys.modular.resource.param.module.SysModuleIdParam;
import vip.xiaonuo.sys.modular.resource.param.module.SysModulePageParam;

import java.util.List;

/**
 * 模块Service接口
 *
 * @author gtc
 *
 **/
public interface SysModuleService extends IService<SysModule> {

    /**
     * 获取模块分页
     *
     * @author gtc
     *
     */
    Page<SysModule> page(SysModulePageParam sysModulePageParam);

    /**
     * 添加模块
     *
     * @author gtc
     *
     */
    void add(SysModuleAddParam sysModuleAddParam);

    /**
     * 编辑模块
     *
     * @author gtc
     *
     */
    void edit(SysModuleEditParam sysModuleEditParam);

    /**
     * 删除模块
     *
     * @author gtc
     *
     */
    void delete(List<SysModuleIdParam> sysModuleIdParamList);

    /**
     * 获取模块详情
     *
     * @author gtc
     *
     */
    SysModule detail(SysModuleIdParam sysModuleIdParam);

    /**
     * 获取模块详情
     *
     * @author gtc
     *
     */
    SysModule queryEntity(String id);
}
