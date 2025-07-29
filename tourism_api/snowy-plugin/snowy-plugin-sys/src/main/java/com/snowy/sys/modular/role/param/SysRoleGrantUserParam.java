
package com.snowy.sys.modular.role.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色授权用户参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class SysRoleGrantUserParam {

    /** 角色id */
    @ApiModelProperty(value = "角色id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 授权用户信息 */
    @ApiModelProperty(value = "授权用户信息", required = true, position = 2)
    @NotNull(message = "grantInfoList不能为空")
    private List<String> grantInfoList;
}
