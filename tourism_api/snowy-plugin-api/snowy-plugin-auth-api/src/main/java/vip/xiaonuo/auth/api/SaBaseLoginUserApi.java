
package vip.xiaonuo.auth.api;

import cn.hutool.json.JSONObject;
import vip.xiaonuo.auth.core.pojo.SaBaseClientLoginUser;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;

import java.util.List;

/**
 * 登录用户API，由其他模块实现
 *
 * @author gtc
 * 
 */
public interface SaBaseLoginUserApi {

    /**
     * 根据id获取B端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseLoginUser getUserById(String id);

    /**
     * 根据id获取C端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseClientLoginUser getClientUserById(String id);

    /**
     * 根据账号获取B端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseLoginUser getUserByAccount(String account);

    /**
     * 根据账号获取C端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseClientLoginUser getClientUserByAccount(String account);

    /**
     * 根据手机号获取B端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseLoginUser getUserByPhone(String phone);

    /**
     * 根据手机号获取C端用户信息，查不到则返回null
     *
     * @author gtc
     * 
     **/
    SaBaseClientLoginUser getClientUserByPhone(String phone);

    /**
     * 根据用户id获取用户集合
     *
     * @author gtc
     * 
     */
    List<JSONObject> listUserByUserIdList(List<String> userIdList);

    /**
     * 根据用户id获取角色集合
     *
     * @author gtc
     * 
     */
    List<JSONObject> getRoleListByUserId(String userId);

    /**
     * 根据角色id和用户id集合获取按钮码集合
     *
     * @author gtc
     * 
     */
    List<String> getButtonCodeListListByUserAndRoleIdList(List<String> userAndRoleIdList);

    /**
     * 根据角色id和用户id集合获取移动端按钮码集合
     *
     * @author gtc
     * 
     */
//    List<String> getMobileButtonCodeListListByUserIdAndRoleIdList(List<String> userAndRoleIdList);

    /**
     * 根据角色id和用户id集合获取权限集合
     *
     * @author gtc
     * 
     */
    List<JSONObject> getPermissionListByUserIdAndRoleIdList(List<String> userAndRoleIdList, String orgId);

    /**
     * 更新用户的登录时间和登录ip等信息
     *
     * @author gtc
     * 
     */
    void updateUserLoginInfo(String userId, String device);
}
