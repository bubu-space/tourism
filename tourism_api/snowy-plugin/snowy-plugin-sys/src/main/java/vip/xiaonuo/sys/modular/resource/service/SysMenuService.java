
package vip.xiaonuo.sys.modular.resource.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.resource.entity.SysMenu;
import vip.xiaonuo.sys.modular.resource.entity.SysModule;
import vip.xiaonuo.sys.modular.resource.param.menu.*;

import java.util.List;

/**
 * 菜单Service接口
 *
 * @author gtc
 *
 **/
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 获取菜单分页
     *
     * @author gtc
     *
     */
    Page<SysMenu> page(SysMenuPageParam sysMenuPageParam);

    /**
     * 获取菜单树
     *
     * @author gtc
     *
     */
    List<Tree<String>> tree(SysMenuTreeParam sysMenuTreeParam);

    /**
     * 添加菜单
     *
     * @author gtc
     *
     */
    void add(SysMenuAddParam sysMenuAddParam);

    /**
     * 代码生成菜单插入
     *
     * @author gtc
     *
     **/
    String addForGenMenu(String parentId, String busName, String title, String module, String path);

    /**
     * 编辑菜单
     *
     * @author gtc
     *
     */
    void edit(SysMenuEditParam sysMenuEditParam);

    /**
     * 更改菜单所属模块
     *
     * @author gtc
     *
     **/
    void changeModule(SysMenuChangeModuleParam sysMenuChangeModuleParam);

    /**
     * 删除菜单
     *
     * @author gtc
     *
     */
    void delete(List<SysMenuIdParam> sysMenuIdParamList);

    /**
     * 获取菜单详情
     *
     * @author gtc
     *
     */
    SysMenu detail(SysMenuIdParam sysMenuIdParam);

    /**
     * 获取菜单详情
     *
     * @author gtc
     *
     */
    SysMenu queryEntity(String id);

    /* ====以下为各种递归方法==== */

    /**
     * 根据id获取所有的子数据列表
     *
     * @author gtc
     *
     */
    List<SysMenu> getChildListById(List<SysMenu> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     *
     * @author gtc
     *
     */
    List<SysMenu> getParentListById(List<SysMenu> originDataList, String id, boolean includeSelf);

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     *
     * @author gtc
     *
     */
    List<SysModule> moduleSelector(SysMenuSelectorModuleParam sysMenuSelectorModuleParam);

    /**
     * 获取菜单树选择器
     *
     * @author gtc
     *
     */
    List<Tree<String>> menuTreeSelector(SysMenuSelectorMenuParam sysMenuSelectorMenuParam);
}
