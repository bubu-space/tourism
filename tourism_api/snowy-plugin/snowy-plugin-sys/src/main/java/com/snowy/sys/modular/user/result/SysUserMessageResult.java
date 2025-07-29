
package com.snowy.sys.modular.user.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户未读消息结果
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class SysUserMessageResult {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 2)
    private String category;

    /** 主题 */
    @ApiModelProperty(value = "主题", position = 3)
    private String subject;

    /** 正文 */
    @ApiModelProperty(value = "正文", position = 4)
    private String content;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 5)
    private String extJson;

    /** 是否已读 */
    @ApiModelProperty(value = "是否已读", position = 6)
    private Boolean read;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 7)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 8)
    private String createUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 9)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 10)
    private String updateUser;
}
