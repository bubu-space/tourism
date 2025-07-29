package com.snowy.client.modular.wxindex.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import com.snowy.biz.modular.spot.converter.SpotImageTypeHandler;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

public class ActivitieInfo {
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 所属景点 */
    @ApiModelProperty(value = "所属景点", position = 2)
    private String spotName;

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
}
