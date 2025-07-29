
package com.snowy.sys.modular.role.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色拥有的资源结果
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysRoleOwnResourceResult {

    /** 角色id */
    @ApiModelProperty(value = "角色id", position = 1)
    private String id;

    /** 已授权资源信息 */
    @ApiModelProperty(value = "已授权资源信息", position = 2)
    private List<SysRoleOwnResource> grantInfoList;

    /**
     * 角色拥有资源类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class SysRoleOwnResource {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        private String menuId;

        /** 按钮id集合 */
        @ApiModelProperty(value = "按钮id集合", position = 2)
        private List<String> buttonInfo;
    }
}
