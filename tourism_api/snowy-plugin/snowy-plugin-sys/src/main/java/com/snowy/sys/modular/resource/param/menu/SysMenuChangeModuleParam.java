
package com.snowy.sys.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 菜单更改所属模块参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysMenuChangeModuleParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 模块 */
    @ApiModelProperty(value = "module", required = true)
    @NotBlank(message = "module不能为空")
    private String module;
}
