
package com.snowy.auth.modular.third.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 第三方登录回调参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class AuthThirdCallbackParam {

    /** 第三方平台标识 */
    @ApiModelProperty(value = "第三方平台标识", required = true, position = 1)
    @NotBlank(message = "platform不能为空")
    private String platform;

    /** 第三方回调code */
    @ApiModelProperty(value = "第三方回调code", required = true, position = 2)
    @NotBlank(message = "code不能为空")
    private String code;

    /** 第三方回调state */
    @ApiModelProperty(value = "第三方回调state", required = true, position = 3)
    @NotBlank(message = "state不能为空")
    private String state;
}
