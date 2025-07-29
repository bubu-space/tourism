
package com.snowy.dev.modular.config.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 配置Id参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevConfigIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
