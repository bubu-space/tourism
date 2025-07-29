
package com.snowy.sys.modular.resource.param.button;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 按钮Id参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysButtonIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
