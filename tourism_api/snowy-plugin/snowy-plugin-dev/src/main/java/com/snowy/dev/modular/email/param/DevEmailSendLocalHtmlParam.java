
package com.snowy.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送——本地HTML参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class DevEmailSendLocalHtmlParam {

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

    /** 图片展位符 */
    @ApiModelProperty(value = "图片展位符", position = 4, hidden = true)
    private Map<String, InputStream> imageMap = MapUtil.newHashMap();

    /** 附件列表 */
    @ApiModelProperty(value = "附件列表", position = 5, hidden = true)
    private List<File> files = CollectionUtil.newArrayList();
}
