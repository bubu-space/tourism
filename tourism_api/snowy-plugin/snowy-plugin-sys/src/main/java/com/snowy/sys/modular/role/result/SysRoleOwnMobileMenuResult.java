
package com.snowy.sys.modular.role.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色拥有的移动端菜单结果
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysRoleOwnMobileMenuResult {

    /** 角色id */
    @ApiModelProperty(value = "角色id", position = 1)
    private String id;

    /** 已授权移动端菜单信息 */
    @ApiModelProperty(value = "已授权移动端菜单信息", position = 2)
    private List<SysRoleOwnMobileMenu> grantInfoList;

    /**
     * 角色拥有移动端菜单类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class SysRoleOwnMobileMenu {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        private String menuId;

        /** 按钮id集合 */
        @ApiModelProperty(value = "按钮id集合", position = 2)
        private List<String> buttonInfo;
    }
}
