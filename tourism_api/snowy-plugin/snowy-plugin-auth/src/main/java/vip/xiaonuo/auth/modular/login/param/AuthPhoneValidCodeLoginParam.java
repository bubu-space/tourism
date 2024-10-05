
package vip.xiaonuo.auth.modular.login.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 手机验证码登录参数
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class AuthPhoneValidCodeLoginParam {

    /** 手机号 */
    @ApiModelProperty(value = "手机号", required = true, position = 1)
    @NotBlank(message = "手机号不能为空")
    private String phone;

    /** 验证码 */
    @ApiModelProperty(value = "验证码", required = true, position = 2)
    @NotBlank(message = "验证码不能为空")
    private String validCode;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", required = true, position = 3)
    @NotBlank(message = "验证码请求号不能为空")
    private String validCodeReqNo;

    /** 设备 */
    @ApiModelProperty(value = "设备", position = 4)
    private String device;
}
