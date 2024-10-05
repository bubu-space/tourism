
package vip.xiaonuo.sys.modular.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.org.entity.SysOrg;
import vip.xiaonuo.sys.modular.org.param.*;
import vip.xiaonuo.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 组织Service接口
 *
 * @author gtc
 *
 **/
public interface SysOrgService extends IService<SysOrg> {

    /**
     * 获取组织分页
     *
     * @author gtc
     *
     */
    Page<SysOrg> page(SysOrgPageParam sysOrgPageParam);

    /**
     * 获取组织树
     *
     * @author gtc
     *
     */
    List<Tree<String>> tree();

    /**
     * 添加组织
     *
     * @author gtc
     *
     */
    void add(SysOrgAddParam sysOrgAddParam);

    /**
     * 编辑组织
     *
     * @author gtc
     *
     */
    void edit(SysOrgEditParam sysOrgEditParam);

    /**
     * 删除组织
     *
     * @author gtc
     *
     */
    void delete(List<SysOrgIdParam> sysOrgIdParamList);

    /**
     * 获取组织详情
     *
     * @author gtc
     *
     */
    SysOrg detail(SysOrgIdParam sysOrgIdParam);

    /**
     * 获取组织详情
     *
     * @author gtc
     *
     **/
    SysOrg queryEntity(String id);

    /**
     * 获取所有组织
     *
     * @author gtc
     *
     **/
    List<SysOrg> getAllOrgList();

    /**
     * 根据组织全名称获取组织id，有则返回，无则创建
     *
     * @author gtc
     *
     **/
    String getOrgIdByOrgFullNameWithCreate(String orgFullName);

    /**
     * 根据id获取父子数据列表
     *
     * @author gtc
     *
     **/
    List<SysOrg> getParentAndChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的子数据列表
     *
     * @author gtc
     *
     **/
    List<SysOrg> getChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     *
     * @author gtc
     *
     **/
    List<SysOrg> getParentListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取数据
     *
     * @author gtc
     *
     **/
    SysOrg getById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取父数据
     *
     * @author gtc
     *
     **/
    SysOrg getParentById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取子数据
     *
     * @author gtc
     *
     **/
    SysOrg getChildById(List<SysOrg> originDataList, String id);

    /**
     * 获取组织树选择器
     *
     * @author gtc
     *
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     *
     * @author gtc
     *
     **/
    Page<SysOrg> orgListSelector(SysOrgSelectorOrgListParam sysOrgSelectorOrgListParam);

    /**
     * 获取用户选择器
     *
     * @author gtc
     *
     */
    Page<SysUser> userSelector(SysOrgSelectorUserParam sysOrgSelectorUserParam);
}
