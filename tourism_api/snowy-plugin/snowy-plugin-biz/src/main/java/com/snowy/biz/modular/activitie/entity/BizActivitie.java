
package com.snowy.biz.modular.activitie.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.converter.SpotImageTypeHandler;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 景区/景点活动表实体
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
@TableName(value = "biz_activitie", autoResultMap = true)
public class BizActivitie {

    /** id */
    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 所属景点 */
    @ApiModelProperty(value = "所属景点", position = 2)
    private String spotId;

    /** 活动标题 */
    @ApiModelProperty(value = "活动标题", position = 3)
    private String title;

    /** 图片 */
    @ApiModelProperty(value = "图片", position = 4)
    @TableField(value = "image",typeHandler = SpotImageTypeHandler.class)
    private SpotImageUrlPair image;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 5)
    private String content;

    /** DELETE_FLAG */
    @ApiModelProperty(value = "DELETE_FLAG", position = 6)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** CREATE_USER */
    @ApiModelProperty(value = "CREATE_USER", position = 7)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** CREATE_TIME */
    @ApiModelProperty(value = "CREATE_TIME", position = 8)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** UPDATE_USER */
    @ApiModelProperty(value = "UPDATE_USER", position = 9)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** UPDATE_TIME */
    @ApiModelProperty(value = "UPDATE_TIME", position = 10)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** VIEW_COUNT */
    @ApiModelProperty(value = "VIEW_COUNT", position = 11)
    private Integer viewCount;

    @TableField(exist = false)
    private String spotName;

    @TableField(exist = false)
    private String createUserName;
}
