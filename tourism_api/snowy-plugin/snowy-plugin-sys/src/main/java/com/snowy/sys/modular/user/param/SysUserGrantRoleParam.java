
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户授权角色参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserGrantRoleParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 角色id集合 */
    @ApiModelProperty(value = "角色id集合", required = true, position = 2)
    @NotNull(message = "roleIdList不能为空")
    private List<String> roleIdList;
}
