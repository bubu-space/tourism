
package com.snowy.sys.modular.role.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色拥有的权限结果
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysRoleOwnPermissionResult {

    /** 角色id */
    @ApiModelProperty(value = "角色id", position = 1)
    private String id;

    /** 已授权权限信息 */
    @ApiModelProperty(value = "已授权权限信息", position = 2)
    private List<SysRoleOwnPermission> grantInfoList;

    /**
     * 角色拥有资源类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class SysRoleOwnPermission {

        /** 菜单id */
        @ApiModelProperty(value = "接口地址", position = 1)
        private String apiUrl;

        /** 数据范围分类 */
        @ApiModelProperty(value = "数据范围分类", position = 1)
        private String scopeCategory;

        /** 自定义范围组织id集合 */
        @ApiModelProperty(value = "自定义范围组织id集合", position = 2)
        private List<String> scopeDefineOrgIdList;

    }
}
