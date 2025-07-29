
package com.snowy.sys.modular.index.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 站内信列表结果
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class SysIndexMessageListResult {

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

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 6)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 7)
    private String createUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 8)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 9)
    private String updateUser;
}
