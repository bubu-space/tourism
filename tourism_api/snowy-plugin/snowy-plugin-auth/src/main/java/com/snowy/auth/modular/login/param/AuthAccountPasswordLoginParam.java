
package com.snowy.auth.modular.login.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 账号密码登录参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class AuthAccountPasswordLoginParam {

    /** 账号 */
    @ApiModelProperty(value = "账号", required = true, position = 1)
    @NotBlank(message = "账号不能为空")
    private String account;

    /** 密码 */
    @ApiModelProperty(value = "密码", required = true, position = 2)
    @NotBlank(message = "密码不能为空")
    private String password;

    /** 设备 */
    @ApiModelProperty(value = "设备", position = 3)
    private String device;

    /** 验证码 */
    @ApiModelProperty(value = "验证码", position = 4)
    private String validCode;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", position = 5)
    private String validCodeReqNo;
}
