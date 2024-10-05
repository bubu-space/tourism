
package vip.xiaonuo.auth.modular.login.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 图片验证码结果
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class AuthPicValidCodeResult {

    /** 验证码图片，Base64 */
    @ApiModelProperty(value = "验证码图片，Base64", position = 1)
    private String validCodeBase64;

    /** 验证码请求号 */
    @ApiModelProperty(value = "验证码请求号", position = 2)
    private String validCodeReqNo;
}
