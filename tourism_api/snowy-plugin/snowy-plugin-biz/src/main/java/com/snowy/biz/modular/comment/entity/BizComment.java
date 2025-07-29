
package com.snowy.biz.modular.comment.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评价留言表实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName("biz_comment")
public class BizComment {

    /** id */
    @TableId
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 2)
    private String userId;

    /** 订单id */
    @ApiModelProperty(value = "订单id", position = 3)
    private String orderId;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 4)
    private String content;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 5)
    private Integer likeCount;

    private Boolean audit;
    /** 打分 */
    @ApiModelProperty(value = "打分", position = 6)
    private Double score;

    /** 删除标志 */
    @ApiModelProperty(value = "删除标志", position = 7)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 8)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 9)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 10)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 11)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    //用户名
    @TableField(exist = false)
    private String userName;
    //用户头像
    @TableField(exist = false)
    private String userAvatar;
}
