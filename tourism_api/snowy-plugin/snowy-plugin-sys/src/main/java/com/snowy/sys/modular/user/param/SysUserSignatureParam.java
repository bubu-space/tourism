
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户修改签名图片接口
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserSignatureParam {

    /** 签名图片base64编码 */
    @ApiModelProperty(value = "signature", required = true)
    @NotBlank(message = "signature签名图片不能为空")
    private String signature;
}
