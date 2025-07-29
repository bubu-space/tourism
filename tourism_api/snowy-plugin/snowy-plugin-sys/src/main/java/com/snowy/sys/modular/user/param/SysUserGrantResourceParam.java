
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户授权资源参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserGrantResourceParam {

    /** 用户id */
    @ApiModelProperty(value = "用户id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 授权资源信息 */
    @Valid
    @ApiModelProperty(value = "授权资源信息", required = true, position = 2)
    @NotNull(message = "grantInfoList不能为空")
    private List<SysUserGrantResource> grantInfoList;

    /**
     * 用户授权资源类
     *
     * @author snowy
     *
     */
    @Getter
    @Setter
    public static class SysUserGrantResource {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        @NotBlank(message = "menuId不能为空")
        private String menuId;

        /** 按钮id集合 */
        @ApiModelProperty(value = "按钮id集合", position = 2)
        @NotNull(message = "buttonInfo不能为空")
        private List<String> buttonInfo;
    }
}
