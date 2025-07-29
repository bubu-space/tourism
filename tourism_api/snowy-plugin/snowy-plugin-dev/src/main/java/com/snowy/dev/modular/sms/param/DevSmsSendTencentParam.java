
package com.snowy.dev.modular.sms.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 短信发送——阿里云参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevSmsSendTencentParam {

    /** 手机号 */
    @ApiModelProperty(value = "手机号，多个逗号拼接", required = true, position = 1)
    @NotBlank(message = "phoneNumbers不能为空")
    private String phoneNumbers;

    /** 模板编码 */
    @ApiModelProperty(value = "短信服务控制台配置且审核通过的模板编码", required = true, position = 2)
    @NotBlank(message = "templateCode不能为空")
    private String templateCode;

    /** 发送参数 */
    @ApiModelProperty(value = "短信模板变量对应的顺序。支持传入多个参数，逗号拼接", position = 3)
    private String templateParam;

    /** sdkAppId */
    @ApiModelProperty(value = "在短信控制台添加应用后生成的实际SdkAppId", position = 4)
    private String sdkAppId;

    /** 短信签名 */
    @ApiModelProperty(value = "短信服务控制台配置且审核通过的短信签名", position = 5)
    private String signName;
}
