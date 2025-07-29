
package com.snowy.sys.modular.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.sys.modular.org.entity.SysOrg;
import com.snowy.sys.modular.org.param.*;
import com.snowy.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 组织Service接口
 *
 * @author snowy
 *
 **/
public interface SysOrgService extends IService<SysOrg> {

    /**
     * 获取组织分页
     *
     * @author snowy
     *
     */
    Page<SysOrg> page(SysOrgPageParam sysOrgPageParam);

    /**
     * 获取组织树
     *
     * @author snowy
     *
     */
    List<Tree<String>> tree();

    /**
     * 添加组织
     *
     * @author snowy
     *
     */
    void add(SysOrgAddParam sysOrgAddParam);

    /**
     * 编辑组织
     *
     * @author snowy
     *
     */
    void edit(SysOrgEditParam sysOrgEditParam);

    /**
     * 删除组织
     *
     * @author snowy
     *
     */
    void delete(List<SysOrgIdParam> sysOrgIdParamList);

    /**
     * 获取组织详情
     *
     * @author snowy
     *
     */
    SysOrg detail(SysOrgIdParam sysOrgIdParam);

    /**
     * 获取组织详情
     *
     * @author snowy
     *
     **/
    SysOrg queryEntity(String id);

    /**
     * 获取所有组织
     *
     * @author snowy
     *
     **/
    List<SysOrg> getAllOrgList();

    /**
     * 根据组织全名称获取组织id，有则返回，无则创建
     *
     * @author snowy
     *
     **/
    String getOrgIdByOrgFullNameWithCreate(String orgFullName);

    /**
     * 根据id获取父子数据列表
     *
     * @author snowy
     *
     **/
    List<SysOrg> getParentAndChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的子数据列表
     *
     * @author snowy
     *
     **/
    List<SysOrg> getChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     *
     * @author snowy
     *
     **/
    List<SysOrg> getParentListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取数据
     *
     * @author snowy
     *
     **/
    SysOrg getById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取父数据
     *
     * @author snowy
     *
     **/
    SysOrg getParentById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取子数据
     *
     * @author snowy
     *
     **/
    SysOrg getChildById(List<SysOrg> originDataList, String id);

    /**
     * 获取组织树选择器
     *
     * @author snowy
     *
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     *
     * @author snowy
     *
     **/
    Page<SysOrg> orgListSelector(SysOrgSelectorOrgListParam sysOrgSelectorOrgListParam);

    /**
     * 获取用户选择器
     *
     * @author snowy
     *
     */
    Page<SysUser> userSelector(SysOrgSelectorUserParam sysOrgSelectorUserParam);
}
