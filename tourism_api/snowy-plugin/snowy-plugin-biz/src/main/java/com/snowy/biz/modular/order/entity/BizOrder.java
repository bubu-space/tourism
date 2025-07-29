
package com.snowy.biz.modular.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 门票订单表实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName("biz_order")
public class BizOrder {

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 2)
    private String userId;

    /** 支付时间 */
    @ApiModelProperty(value = "预约时间", position = 3)
    private Date reservationTime;

    /** 支付方式 */
    @ApiModelProperty(value = "支付方式", position = 4)
    private String payMode;

    /** 金额 */
    @ApiModelProperty(value = "金额", position = 5)
    private BigDecimal price;

    /** 门票id */
    @ApiModelProperty(value = "门票id", position = 6)
    private String ticketId;

    /** 门票数量 */
    @ApiModelProperty(value = "门票数量", position = 7)
    private Integer ticketCount;


    /** 订单状态：待支付/已支付/待完成/已完成/未评价 */
    @ApiModelProperty(value = "订单状态：待支付/已支付/待完成/已完成/未评价", position = 9)
    private String status;

    /** DELETE_FLAG */
    @ApiModelProperty(value = "DELETE_FLAG", position = 10)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** CREATE_USER */
    @ApiModelProperty(value = "CREATE_USER", position = 11)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** CREATE_TIME */
    @ApiModelProperty(value = "CREATE_TIME", position = 12)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** UPDATE_USER */
    @ApiModelProperty(value = "UPDATE_USER", position = 13)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** UPDATE_TIME */
    @ApiModelProperty(value = "UPDATE_TIME", position = 14)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "景区名称", position = 15)
    private String spotName;

    @ApiModelProperty(value = "门票名称", position = 16)
    private String ticketName;

    @ApiModelProperty(value = "景区图片", position = 17)
    @TableField(exist = false)
    private SpotImageUrlPair spotimage;
}
