
package com.snowy.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 邮件发送——腾讯云TMP参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class DevEmailSendTencentTmpParam {

    /** 发件人邮箱 */
    @ApiModelProperty(value = "管理控制台中配置的发信地址", required = true, position = 1)
    @NotBlank(message = "sendAccount不能为空")
    private String sendAccount;

    /** 接收人 */
    @ApiModelProperty(value = "预先创建且上传了收件人的收件人列表id", required = true, position = 2)
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /** 模板名 */
    @ApiModelProperty(value = "预先创建且通过审核的模板Id", required = true, position = 3)
    @NotBlank(message = "templateName不能为空")
    private String templateName;

    /** 邮件主题 */
    @ApiModelProperty(value = "邮件主题", required = true, position = 4)
    @NotBlank(message = "subject不能为空")
    private String subject;

    /** 发件人昵称 */
    @ApiModelProperty(value = "发件人昵称", position = 5)
    private String sendUser;

    /** 发送参数 */
    @ApiModelProperty(value = "预先创建且通过审核的模板的参数json", position = 6)
    private String templateParam;

    /** 附件列表 */
    @ApiModelProperty(value = "附件列表", position = 7, hidden = true)
    List<JSONObject> attachmentList = CollectionUtil.newArrayList();
}
