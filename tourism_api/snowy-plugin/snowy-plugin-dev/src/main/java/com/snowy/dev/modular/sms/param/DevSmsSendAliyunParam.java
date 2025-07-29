
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
public class DevSmsSendAliyunParam {

    /** 手机号 */
    @ApiModelProperty(value = "手机号，多个逗号拼接", required = true, position = 1)
    @NotBlank(message = "phoneNumbers不能为空")
    private String phoneNumbers;

    /** 模板编码 */
    @ApiModelProperty(value = "短信服务控制台配置且审核通过的模板编码", required = true, position = 2)
    @NotBlank(message = "templateCode不能为空")
    private String templateCode;

    /** 发送参数 */
    @ApiModelProperty(value = "短信模板变量对应的实际值，JSON格式", position = 3)
    private String templateParam;

    /** 短信签名 */
    @ApiModelProperty(value = "短信服务控制台配置且审核通过的短信签名", position = 4)
    private String signName;
}
