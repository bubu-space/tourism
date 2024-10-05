
package vip.xiaonuo.sys.modular.role.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色授权移动端菜单树结果
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysRoleGrantMobileMenuTreeResult {

    /** 模块id */
    @ApiModelProperty(value = "模块id", position = 1)
    private String id;

    /** 模块名称*/
    @ApiModelProperty(value = "模块名称", position = 2)
    private String title;

    /** 模块图标 */
    @ApiModelProperty(value = "模块图标", position = 3)
    private String icon;

    /** 模块下移动端菜单集合*/
    @ApiModelProperty(value = "模块下移动端菜单集合", position = 4)
    private List<SysRoleGrantMobileMenuResult> menu;

    /**
     * 授权移动端菜单类
     *
     * @author gtc
     *
     */
    @Getter
    @Setter
    public static class SysRoleGrantMobileMenuResult {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        private String id;

        /** 父id */
        @ApiModelProperty(value = "父id", position = 2)
        private String parentId;

        /** 父名称 */
        @ApiModelProperty(value = "菜单名称", position = 3)
        private String parentName;

        /** 标题 */
        @ApiModelProperty(value = "菜单标题", position = 4)
        private String title;

        /** 模块 */
        @ApiModelProperty(value = "菜单模块", position = 5)
        private String module;

        /** 菜单下按钮集合 */
        @ApiModelProperty(value = "菜单下按钮集合", position = 6)
        private List<SysRoleGrantMobileButtonResult> button;

        /**
         * 授权按钮类
         *
         * @author gtc
         *
         */
        @Getter
        @Setter
        public static class SysRoleGrantMobileButtonResult {

            /** 按钮id */
            @ApiModelProperty(value = "按钮id", position = 1)
            private String id;

            /** 标题 */
            @ApiModelProperty(value = "按钮标题", position = 2)
            private String title;
        }
    }
}
