
package vip.xiaonuo.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;

/**
 * 邮件发送——本地TXT参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class DevEmailSendLocalTxtParam {

    /** 接收人 */
    @ApiModelProperty(value = "接收人邮箱地址，多个逗号拼接", required = true, position = 1)
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /** 邮件主题 */
    @ApiModelProperty(value = "邮件主题", required = true, position = 2)
    @NotBlank(message = "subject不能为空")
    private String subject;

    /** 邮件正文 */
    @ApiModelProperty(value = "邮件正文", required = true, position = 3)
    @NotBlank(message = "content不能为空")
    private String content;

    /** 附件列表 */
    @ApiModelProperty(value = "附件列表", position = 4, hidden = true)
    private List<File> files = CollectionUtil.newArrayList();
}
