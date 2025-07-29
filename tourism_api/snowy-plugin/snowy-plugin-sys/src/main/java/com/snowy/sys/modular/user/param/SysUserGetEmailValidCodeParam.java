
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 获取邮箱验证码参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserGetEmailValidCodeParam {

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", required = true, position = 1)
    @NotBlank(message = "邮箱不能为空")
    private String email;

    /** 验证码 */
    @ApiModelProperty(value = "验证码", required = true, position = 2)
    @NotBlank(message = "验证码不能为空")
    private String validCode;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", required = true, position = 3)
    @NotBlank(message = "验证码请求号不能为空")
    private String validCodeReqNo;
}
