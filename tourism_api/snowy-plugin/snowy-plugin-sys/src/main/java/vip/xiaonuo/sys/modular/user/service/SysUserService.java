
package vip.xiaonuo.sys.modular.user.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.sys.modular.org.entity.SysOrg;
import vip.xiaonuo.sys.modular.position.entity.SysPosition;
import vip.xiaonuo.sys.modular.role.entity.SysRole;
import vip.xiaonuo.sys.modular.user.entity.SysUser;
import vip.xiaonuo.sys.modular.user.param.*;
import vip.xiaonuo.sys.modular.user.result.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户Service接口
 *
 * @author gtc
 * 
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id获取用户信息，查不到则返回null
     *
     * @author gtc
     * 
     */
    SysLoginUser getUserById(String id);

    /**
     * 根据账户获取用户信息，查不到则返回null
     *
     * @author gtc
     * 
     */
    SysLoginUser getUserByAccount(String account);

    /**
     * 根据手机号获取用户信息，查不到则返回null
     *
     * @author gtc
     * 
     */
    SysLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息，查不到则返回null
     *
     * @author gtc
     * 
     */
    SysLoginUser getUserByEmail(String email);

    /**
     * 获取用户分页
     *
     * @author gtc
     * 
     */
    Page<SysUser> page(SysUserPageParam sysUserPageParam);

    /**
     * 添加用户
     *
     * @author gtc
     * 
     */
    void add(SysUserAddParam sysUserAddParam);

    /**
     * 编辑用户
     *
     * @author gtc
     * 
     */
    void edit(SysUserEditParam sysUserEditParam);

    /**
     * 删除用户
     *
     * @author gtc
     * 
     */
    void delete(List<SysUserIdParam> sysUserIdParamList);

    /**
     * 获取用户详情
     *
     * @author gtc
     * 
     */
    SysUser detail(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户详情
     *
     * @author gtc
     * 
     **/
    SysUser queryEntity(String id);

    /**
     * 禁用用户
     *
     * @author gtc
     * 
     **/
    void disableUser(SysUserIdParam sysUserIdParam);

    /**
     * 启用用户
     *
     * @author gtc
     * 
     **/
    void enableUser(SysUserIdParam sysUserIdParam);

    /**
     * 重置用户密码
     *
     * @author gtc
     * 
     **/
    void resetPassword(SysUserIdParam sysUserIdParam);

    /**
     * 获取图片验证码
     *
     * @author gtc
     * 
     **/
    SysUserPicValidCodeResult getPicCaptcha();

    /**
     * 找回密码获取手机验证码
     *
     * @author gtc
     * 
     **/
    String findPasswordGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 找回密码获取邮箱验证码
     *
     * @author gtc
     * 
     **/
    String findPasswordGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 通过手机号找回用户密码
     *
     * @author gtc
     * 
     **/
    void findPasswordByPhone(SysUserFindPwdByPhoneParam sysUserFindPwdByPhoneParam);

    /**
     * 通过邮箱找回用户密码
     *
     * @author gtc
     * 
     **/
    void findPasswordByEmail(SysUserFindPwdByEmailParam sysUserFindPwdByEmailParam);

    /**
     * 修改用户密码
     *
     * @author gtc
     * 
     **/
    void updatePassword(SysUserUpdatePwdParam sysUserUpdatePwdParam);

    /**
     * 修改用户头像返回base64
     *
     * @author gtc
     * 
     **/
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户签名图片返回base64
     *
     * @author gtc
     * 
     **/
    void updateSignature(SysUserSignatureParam sysUserSignatureParam);

    /**
     * 更新用户的登录时间和登录ip等信息
     *
     * @author gtc
     * 
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 获取用户拥有菜单
     *
     * @author gtc
     * 
     */
    List<Tree<String>> ownMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有移动端菜单
     *
     * @author gtc
     * 
//     */
//    List<Tree<String>> ownMobileMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有角色
     *
     * @author gtc
     * 
     */
    List<String> ownRole(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权角色
     *
     * @author gtc
     * 
     **/
    void grantRole(SysUserGrantRoleParam sysUserGrantRoleParam);

    /**
     * 获取用户拥有资源
     *
     * @author gtc
     * 
     */
    SysUserOwnResourceResult ownResource(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权资源
     *
     * @author gtc
     * 
     **/
    void grantResource(SysUserGrantResourceParam sysUserGrantResourceParam);

    /**
     * 获取用户拥有权限
     *
     * @author gtc
     * 
     */
    SysUserOwnPermissionResult ownPermission(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权权限
     *
     * @author gtc
     * 
     **/
    void grantPermission(SysUserGrantPermissionParam sysUserGrantPermissionParam);

    /**
     * 获取用户组织树
     *
     * @author gtc
     * 
     */
    List<Tree<String>> loginOrgTree(SysUserIdParam sysUserIdParam);

    /**
     * 编辑个人信息
     *
     * @author gtc
     * 
     */
    void updateUserInfo(SysUserUpdateInfoParam sysUserUpdateInfoParam);

    /**
     * 编辑个人工作台
     *
     * @author gtc
     * 
     */
    void updateUserWorkbench(SysUserUpdateWorkbenchParam sysUserUpdateWorkbenchParam);

    /**
     * 获取用户工作台数据
     *
     * @author gtc
     * 
     */
    String loginWorkbench(SysUserIdParam sysUserIdParam);

    /**
     * 获取角色集合
     *
     * @author gtc
     * 
     **/
    List<JSONObject> getRoleList(String userId);

    /**
     * 获取按钮编码集合
     *
     * @author gtc
     * 
     **/
    List<String> getButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取移动端按钮编码集合
     *
     * @author gtc
     * 
     **/
//    List<String> getMobileButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取权限集合
     *
     * @author gtc
     * 
     **/
    List<JSONObject> getPermissionList(List<String> userAndRoleIdList, String orgId);

    /**
     * 下载用户导入模板
     *
     * @author gtc
     * 
     **/
    void downloadImportUserTemplate(HttpServletResponse response) throws IOException;

    /**
     * 用户导入
     *
     * @author gtc
     * 
     **/
    JSONObject importUser(MultipartFile file);

    /**
     * 用户导出
     *
     * @author gtc
     * 
     **/
    void exportUser(SysUserExportParam sysUserExportParam, HttpServletResponse response) throws IOException;

    /**
     * 导出用户个人信息
     *
     * @author gtc
     * 
     **/
    void exportUserInfo(SysUserIdParam sysUserIdParam, HttpServletResponse response) throws IOException;

    /**
     * 获取登录用户的职位信息
     *
     * @author gtc
     * 
     **/
    List<SysUserPositionResult> loginPositionInfo(SysUserIdParam sysUserIdParam);

    /**
     * 获取所有用户选择器
     *
     * @author gtc
     * 
     **/
    Page<SysUser> getAllUserSelectorList();

    /* ====用户部分所需要用到的选择器==== */

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
    Page<SysOrg> orgListSelector(SysUserSelectorOrgListParam sysUserSelectorOrgListParam);

    /**
     * 获取职位选择器
     *
     * @author gtc
     * 
     */
    Page<SysPosition> positionSelector(SysUserSelectorPositionParam sysUserSelectorPositionParam);

    /**
     * 获取角色选择器
     *
     * @author gtc
     * 
     */
    Page<SysRole> roleSelector(SysUserSelectorRoleParam sysUserSelectorRoleParam);

    /**
     * 获取用户选择器
     *
     * @author gtc
     * 
     */
    Page<SysUser> userSelector(SysUserSelectorUserParam sysUserSelectorUserParam);

    /**
     * 获取登录用户的站内信分页
     *
     * @author gtc
     * 
     */
    Page<SysUserMessageResult> loginMessagePage(SysUserMessagePageParam sysUserMessagePageParam);

    /**
     * 读取登录用户站内信详情
     *
     * @author gtc
     * 
     */
    SysUserMessageDetailResult loginMessageDetail(SysUserMessageIdParam sysUserMessageIdParam);

    /**
     * 根据id集合获取组织集合
     *
     * @author gtc
     * 
     **/
    List<SysOrg> getOrgListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取用户集合
     *
     * @author gtc
     * 
     **/
    List<SysUser> getUserListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取职位集合
     *
     * @author gtc
     * 
     **/
    List<SysPosition> getPositionListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取角色集合
     *
     * @author gtc
     * 
     **/
    List<SysRole> getRoleListByIdList(SysUserIdListParam sysUserIdListParam);
}
