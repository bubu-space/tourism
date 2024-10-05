
package vip.xiaonuo.dev.modular.email.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 邮件发送——阿里云TMP参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class DevEmailSendAliyunTmpParam {

    /** 发件人邮箱 */
    @ApiModelProperty(value = "管理控制台中配置的发信地址", required = true, position = 1)
    @NotBlank(message = "sendAccount不能为空")
    private String sendAccount;

    /** 接收人 */
    @ApiModelProperty(value = "预先创建且上传了收件人的收件人列表名称", required = true, position = 2)
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /** 模板名 */
    @ApiModelProperty(value = "预先创建且通过审核的模板名称", required = true, position = 4)
    @NotBlank(message = "templateName不能为空")
    private String templateName;

    /** 标签名 */
    @ApiModelProperty(value = "标签名", position = 5)
    private String tagName;
}
