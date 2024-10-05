
package vip.xiaonuo.sys.api;

import java.util.List;

/**
 * 关系API
 *
 * @author gtc
 *
 **/
public interface SysRelationApi {

    /**
     * 根据角色id集合获取角色下用户id集合
     *
     * @author gtc
     *
     **/
    List<String> getUserIdListByRoleIdList(List<String> roleIdList);

    /**
     * 根据移动端菜单Id集合移除角色和移动端菜单关系
     *
     * @author gtc
     *
     **/
    void removeRoleHasMobileMenuRelation(List<String> targetIdList);

    /**
     * 清除对应的角色与移动端菜单信息中的【授权的移动端按钮信息】
     *
     * @author gtc
     *
     **/
    void removeRoleHasMobileButtonRelation(List<String> targetIdList, List<String> buttonIdList);
}
