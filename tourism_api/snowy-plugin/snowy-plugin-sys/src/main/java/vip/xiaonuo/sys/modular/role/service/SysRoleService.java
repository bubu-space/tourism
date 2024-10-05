
package vip.xiaonuo.sys.modular.role.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.role.entity.SysRole;
import vip.xiaonuo.sys.modular.role.param.*;
import vip.xiaonuo.sys.modular.role.result.*;
import vip.xiaonuo.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 角色Service接口
 *
 * @author gtc
 *
 **/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色分页
     *
     * @author gtc
     *
     */
    Page<SysRole> page(SysRolePageParam sysRolePageParam);

    /**
     * 添加角色
     *
     * @author gtc
     *
     */
    void add(SysRoleAddParam sysRoleAddParam);

    /**
     * 编辑角色
     *
     * @author gtc
     *
     */
    void edit(SysRoleEditParam sysRoleEditParam);

    /**
     * 删除角色
     *
     * @author gtc
     *
     */
    void delete(List<SysRoleIdParam> sysRoleIdParamList);

    /**
     * 获取角色详情
     *
     * @author gtc
     *
     */
    SysRole detail(SysRoleIdParam sysRoleIdParam);

    /**
     * 获取角色详情
     *
     * @author gtc
     *
     **/
    SysRole queryEntity(String id);

    /**
     * 获取角色拥有资源
     *
     * @author gtc
     *
     */
    SysRoleOwnResourceResult ownResource(SysRoleIdParam SysRoleIdParam);

    /**
     * 给角色授权资源
     *
     * @author gtc
     *
     **/
    void grantResource(SysRoleGrantResourceParam sysRoleGrantResourceParam);

    /**
     * 获取角色拥有移动端菜单
     *
     * @author gtc
     *
     */
    SysRoleOwnMobileMenuResult ownMobileMenu(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权移动端菜单
     *
     * @author gtc
     *
     **/
    void grantMobileMenu(SysRoleGrantMobileMenuParam sysRoleGrantMobileMenuParam);

    /**
     * 获取角色拥有权限
     *
     * @author gtc
     *
     */
    SysRoleOwnPermissionResult ownPermission(SysRoleIdParam SysRoleIdParam);

    /**
     * 给角色授权权限
     *
     * @author gtc
     *
     **/
    void grantPermission(SysRoleGrantPermissionParam sysRoleGrantPermissionParam);

    /**
     * 获取角色下的用户
     *
     * @author gtc
     *
     **/
    List<String> ownUser(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权用户
     *
     * @author gtc
     *
     **/
    void grantUser(SysRoleGrantUserParam sysRoleGrantUserParam);

    /* ====角色部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     *
     * @author gtc
     *
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取资源授权树
     *
     * @author gtc
     *
     */
    List<SysRoleGrantResourceTreeResult> resourceTreeSelector();

    /**
     * 获取移动端菜单授权树
     *
     * @author gtc
     *
     */
//    List<SysRoleGrantMobileMenuTreeResult> mobileMenuTreeSelector();

    /**
     * 获取权限授权树
     *
     * @author gtc
     *
     */
    List<String> permissionTreeSelector();

    /**
     * 获取角色选择器
     *
     * @author gtc
     *
     */
    Page<SysRole> roleSelector(SysRoleSelectorRoleParam sysRoleSelectorRoleParam);

    /**
     * 获取用户选择器
     *
     * @author gtc
     *
     **/
    Page<SysUser> userSelector(SysRoleSelectorUserParam sysRoleSelectorUserParam);
}
