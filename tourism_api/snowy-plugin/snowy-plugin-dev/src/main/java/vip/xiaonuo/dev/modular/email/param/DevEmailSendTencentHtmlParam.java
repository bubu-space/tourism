
package vip.xiaonuo.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 邮件发送——腾讯云HTML参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class DevEmailSendTencentHtmlParam {

    /** 发件人邮箱 */
    @ApiModelProperty(value = "管理控制台中配置的发信地址", required = true, position = 1)
    @NotBlank(message = "sendAccount不能为空")
    private String sendAccount;

    /** 接收人 */
    @ApiModelProperty(value = "接收人邮箱地址，多个逗号拼接", required = true, position = 2)
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /** 邮件主题 */
    @ApiModelProperty(value = "邮件主题", required = true, position = 3)
    @NotBlank(message = "subject不能为空")
    private String subject;

    /** 邮件正文 */
    @ApiModelProperty(value = "邮件正文", required = true, position = 4)
    @NotBlank(message = "content不能为空")
    private String content;

    /** 发件人昵称 */
    @ApiModelProperty(value = "发件人昵称", position = 5)
    private String sendUser;

    /** 附件列表 */
    @ApiModelProperty(value = "附件列表", position = 6, hidden = true)
    List<JSONObject> attachmentList = CollectionUtil.newArrayList();
}
