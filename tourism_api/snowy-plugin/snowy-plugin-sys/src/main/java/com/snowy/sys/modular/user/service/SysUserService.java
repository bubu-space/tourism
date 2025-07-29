
package com.snowy.sys.modular.user.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import com.snowy.sys.modular.org.entity.SysOrg;
import com.snowy.sys.modular.position.entity.SysPosition;
import com.snowy.sys.modular.role.entity.SysRole;
import com.snowy.sys.modular.user.entity.SysUser;
import com.snowy.sys.modular.user.param.*;
import com.snowy.sys.modular.user.result.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户Service接口
 *
 * @author snowy
 * 
 **/
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id获取用户信息，查不到则返回null
     *
     * @author snowy
     * 
     */
    SysLoginUser getUserById(String id);

    /**
     * 根据账户获取用户信息，查不到则返回null
     *
     * @author snowy
     * 
     */
    SysLoginUser getUserByAccount(String account);

    /**
     * 根据手机号获取用户信息，查不到则返回null
     *
     * @author snowy
     * 
     */
    SysLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息，查不到则返回null
     *
     * @author snowy
     * 
     */
    SysLoginUser getUserByEmail(String email);

    /**
     * 获取用户分页
     *
     * @author snowy
     * 
     */
    Page<SysUser> page(SysUserPageParam sysUserPageParam);

    /**
     * 添加用户
     *
     * @author snowy
     * 
     */
    void add(SysUserAddParam sysUserAddParam);

    /**
     * 编辑用户
     *
     * @author snowy
     * 
     */
    void edit(SysUserEditParam sysUserEditParam);

    /**
     * 删除用户
     *
     * @author snowy
     * 
     */
    void delete(List<SysUserIdParam> sysUserIdParamList);

    /**
     * 获取用户详情
     *
     * @author snowy
     * 
     */
    SysUser detail(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户详情
     *
     * @author snowy
     * 
     **/
    SysUser queryEntity(String id);

    /**
     * 禁用用户
     *
     * @author snowy
     * 
     **/
    void disableUser(SysUserIdParam sysUserIdParam);

    /**
     * 启用用户
     *
     * @author snowy
     * 
     **/
    void enableUser(SysUserIdParam sysUserIdParam);

    /**
     * 重置用户密码
     *
     * @author snowy
     * 
     **/
    void resetPassword(SysUserIdParam sysUserIdParam);

    /**
     * 获取图片验证码
     *
     * @author snowy
     * 
     **/
    SysUserPicValidCodeResult getPicCaptcha();

    /**
     * 找回密码获取手机验证码
     *
     * @author snowy
     * 
     **/
    String findPasswordGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 找回密码获取邮箱验证码
     *
     * @author snowy
     * 
     **/
    String findPasswordGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 通过手机号找回用户密码
     *
     * @author snowy
     * 
     **/
    void findPasswordByPhone(SysUserFindPwdByPhoneParam sysUserFindPwdByPhoneParam);

    /**
     * 通过邮箱找回用户密码
     *
     * @author snowy
     * 
     **/
    void findPasswordByEmail(SysUserFindPwdByEmailParam sysUserFindPwdByEmailParam);

    /**
     * 修改用户密码
     *
     * @author snowy
     * 
     **/
    void updatePassword(SysUserUpdatePwdParam sysUserUpdatePwdParam);

    /**
     * 修改用户头像返回base64
     *
     * @author snowy
     * 
     **/
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户签名图片返回base64
     *
     * @author snowy
     * 
     **/
    void updateSignature(SysUserSignatureParam sysUserSignatureParam);

    /**
     * 更新用户的登录时间和登录ip等信息
     *
     * @author snowy
     * 
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 获取用户拥有菜单
     *
     * @author snowy
     * 
     */
    List<Tree<String>> ownMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有移动端菜单
     *
     * @author snowy
     * 
//     */
//    List<Tree<String>> ownMobileMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有角色
     *
     * @author snowy
     * 
     */
    List<String> ownRole(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权角色
     *
     * @author snowy
     * 
     **/
    void grantRole(SysUserGrantRoleParam sysUserGrantRoleParam);

    /**
     * 获取用户拥有资源
     *
     * @author snowy
     * 
     */
    SysUserOwnResourceResult ownResource(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权资源
     *
     * @author snowy
     * 
     **/
    void grantResource(SysUserGrantResourceParam sysUserGrantResourceParam);

    /**
     * 获取用户拥有权限
     *
     * @author snowy
     * 
     */
    SysUserOwnPermissionResult ownPermission(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权权限
     *
     * @author snowy
     * 
     **/
    void grantPermission(SysUserGrantPermissionParam sysUserGrantPermissionParam);

    /**
     * 获取用户组织树
     *
     * @author snowy
     * 
     */
    List<Tree<String>> loginOrgTree(SysUserIdParam sysUserIdParam);

    /**
     * 编辑个人信息
     *
     * @author snowy
     * 
     */
    void updateUserInfo(SysUserUpdateInfoParam sysUserUpdateInfoParam);

    /**
     * 编辑个人工作台
     *
     * @author snowy
     * 
     */
    void updateUserWorkbench(SysUserUpdateWorkbenchParam sysUserUpdateWorkbenchParam);

    /**
     * 获取用户工作台数据
     *
     * @author snowy
     * 
     */
    String loginWorkbench(SysUserIdParam sysUserIdParam);

    /**
     * 获取角色集合
     *
     * @author snowy
     * 
     **/
    List<JSONObject> getRoleList(String userId);

    /**
     * 获取按钮编码集合
     *
     * @author snowy
     * 
     **/
    List<String> getButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取移动端按钮编码集合
     *
     * @author snowy
     * 
     **/
//    List<String> getMobileButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取权限集合
     *
     * @author snowy
     * 
     **/
    List<JSONObject> getPermissionList(List<String> userAndRoleIdList, String orgId);

    /**
     * 下载用户导入模板
     *
     * @author snowy
     * 
     **/
    void downloadImportUserTemplate(HttpServletResponse response) throws IOException;

    /**
     * 用户导入
     *
     * @author snowy
     * 
     **/
    JSONObject importUser(MultipartFile file);

    /**
     * 用户导出
     *
     * @author snowy
     * 
     **/
    void exportUser(SysUserExportParam sysUserExportParam, HttpServletResponse response) throws IOException;

    /**
     * 导出用户个人信息
     *
     * @author snowy
     * 
     **/
    void exportUserInfo(SysUserIdParam sysUserIdParam, HttpServletResponse response) throws IOException;

    /**
     * 获取登录用户的职位信息
     *
     * @author snowy
     * 
     **/
    List<SysUserPositionResult> loginPositionInfo(SysUserIdParam sysUserIdParam);

    /**
     * 获取所有用户选择器
     *
     * @author snowy
     * 
     **/
    Page<SysUser> getAllUserSelectorList();

    /* ====用户部分所需要用到的选择器==== */

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
    Page<SysOrg> orgListSelector(SysUserSelectorOrgListParam sysUserSelectorOrgListParam);

    /**
     * 获取职位选择器
     *
     * @author snowy
     * 
     */
    Page<SysPosition> positionSelector(SysUserSelectorPositionParam sysUserSelectorPositionParam);

    /**
     * 获取角色选择器
     *
     * @author snowy
     * 
     */
    Page<SysRole> roleSelector(SysUserSelectorRoleParam sysUserSelectorRoleParam);

    /**
     * 获取用户选择器
     *
     * @author snowy
     * 
     */
    Page<SysUser> userSelector(SysUserSelectorUserParam sysUserSelectorUserParam);

    /**
     * 获取登录用户的站内信分页
     *
     * @author snowy
     * 
     */
    Page<SysUserMessageResult> loginMessagePage(SysUserMessagePageParam sysUserMessagePageParam);

    /**
     * 读取登录用户站内信详情
     *
     * @author snowy
     * 
     */
    SysUserMessageDetailResult loginMessageDetail(SysUserMessageIdParam sysUserMessageIdParam);

    /**
     * 根据id集合获取组织集合
     *
     * @author snowy
     * 
     **/
    List<SysOrg> getOrgListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取用户集合
     *
     * @author snowy
     * 
     **/
    List<SysUser> getUserListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取职位集合
     *
     * @author snowy
     * 
     **/
    List<SysPosition> getPositionListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取角色集合
     *
     * @author snowy
     * 
     **/
    List<SysRole> getRoleListByIdList(SysUserIdListParam sysUserIdListParam);
}
