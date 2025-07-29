package com.snowy.biz.modular.comment.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PageComment {
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

    /** 打分 */
    @ApiModelProperty(value = "打分", position = 6)
    private Double score;

    private Boolean audit;

    /** 景区id */
    @ApiModelProperty(value = "景区id", position = 12)
    private String spotId;

    @ApiModelProperty(value = "时间", position = 13)
    private Date createTime;
}
