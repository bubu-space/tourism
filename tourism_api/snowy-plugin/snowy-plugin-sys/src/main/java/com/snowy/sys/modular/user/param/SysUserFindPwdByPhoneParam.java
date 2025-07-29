
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户找回密码参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserFindPwdByPhoneParam {

    /** 手机号 */
    @ApiModelProperty(value = "手机号", required = true, position = 1)
    @NotBlank(message = "phone不能为空")
    private String phone;

    /** 验证码 */
    @ApiModelProperty(value = "验证码", required = true, position = 2)
    @NotBlank(message = "validCode不能为空")
    private String validCode;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", required = true, position = 3)
    @NotBlank(message = "validCodeReqNo不能为空")
    private String validCodeReqNo;

    /** 新密码 */
    @ApiModelProperty(value = "新密码", required = true, position = 4)
    @NotBlank(message = "newPassword不能为空")
    private String newPassword;
}
