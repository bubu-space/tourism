
package com.snowy.dev.modular.email.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 邮件Id参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class DevEmailIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
