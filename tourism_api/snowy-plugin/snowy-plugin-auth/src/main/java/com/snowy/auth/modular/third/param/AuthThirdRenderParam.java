
package com.snowy.auth.modular.third.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 第三方登录授权参数
 *
 * @author snowy
 * 
 */
@Getter
@Setter
public class AuthThirdRenderParam {

    /** 第三方平台标识 */
    @ApiModelProperty(value = "第三方平台标识", required = true)
    @NotBlank(message = "platform不能为空")
    private String platform;
}
