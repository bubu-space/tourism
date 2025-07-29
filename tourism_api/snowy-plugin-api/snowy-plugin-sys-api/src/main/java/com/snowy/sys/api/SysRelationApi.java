
package com.snowy.sys.api;

import java.util.List;

/**
 * 关系API
 *
 * @author snowy
 *
 **/
public interface SysRelationApi {

    /**
     * 根据角色id集合获取角色下用户id集合
     *
     * @author snowy
     *
     **/
    List<String> getUserIdListByRoleIdList(List<String> roleIdList);

    /**
     * 根据移动端菜单Id集合移除角色和移动端菜单关系
     *
     * @author snowy
     *
     **/
    void removeRoleHasMobileMenuRelation(List<String> targetIdList);

    /**
     * 清除对应的角色与移动端菜单信息中的【授权的移动端按钮信息】
     *
     * @author snowy
     *
     **/
    void removeRoleHasMobileButtonRelation(List<String> targetIdList, List<String> buttonIdList);
}
