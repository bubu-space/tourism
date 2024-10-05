
package vip.xiaonuo.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 用户Api
 *
 * @author gtc
 *
 **/
public interface SysUserApi {

    /**
     * 根据用户id获取用户对象，没有则返回null
     *
     * @author gtc
     *
     **/
    JSONObject getUserByIdWithoutException(String userId);

    /**
     * 根据用户id获取用户对象列表，没有的则为空，都没有则返回空集合
     *
     * @author gtc
     *
     **/
    List<JSONObject> getUserListByIdListWithoutException(List<String> userIdList);

    /**
     * 根据用户id获取用户对象，没有则抛出异常
     *
     * @author gtc
     *
     **/
    JSONObject getUserByIdWithException(String userId);

    /**
     * 根据用户id获取用户对象列表，只要有一个没有则抛出异常
     *
     * @author gtc
     *
     **/
    List<JSONObject> getUserListByIdWithException(List<String> userIdList);

    /**
     * 获取用户拥有角色
     *
     * @author gtc
     *
     */
    List<String> ownRole(String userId);

    /**
     * 给用户授权角色
     *
     * @author gtc
     *
     */
    void grantRole(String userId, List<String> roleIdList);

    /**
     * 根据组织id集合获取组织下用户id集合
     *
     * @author gtc
     *
     **/
    List<String> getUserIdListByOrgIdList(List<String> orgIdList);

    /**
     * 根据职位id集合获取职位下用户id集合
     *
     * @author gtc
     *
     **/
    List<String> getUserIdListByPositionIdList(List<String> positionIdList);

    /**
     * 根据用户id和组织id和职位id和主管层级获取上级主管id
     *
     * @author gtc
     *
     **/
    JSONObject getSupervisorIdBySupervisorLevel(List<String> userIdList, String userId, String orgId, String supervisorLevel);

    /**
     * 根据用户id和组织id和职位id和终点主管层级获取上级主管id集合
     *
     * @author gtc
     *
     **/
    List<String> getMulSupervisorIdListByEndLevel(String userId, String orgId, String endLevel);

    /**
     * 获取用户选择器
     *
     * @author gtc
     *
     */
    Page<JSONObject> userSelector(String orgId, String searchKey);
}
