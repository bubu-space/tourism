
package vip.xiaonuo.auth.modular.third.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 第三方登录授权结果
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class AuthThirdRenderResult {

    /** 授权地址 */
    @ApiModelProperty(value = "授权地址")
    private String authorizeUrl;

    /** 状态码 */
    @ApiModelProperty(value = "状态码")
    private String state;
}
