
package com.snowy.biz.modular.strategy.entity;

import com.aliyun.teautil.Common;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.snowy.common.pojo.CommonEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.converter.SpotImageListTypeHandler;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import java.util.Date;
import java.util.List;

/**
 * 旅游攻略表：由用户发表实体
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
@TableName(value = "biz_strategy", autoResultMap = true)
public class Strategy extends CommonEntity {

    /** 主键 */
    @TableId
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    /** 攻略类型：交通/美食/住宿... */
    @ApiModelProperty(value = "攻略类型：交通/美食/住宿...", position = 2)
    private String category;

    private String userId;

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 3)
    private String title;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 4)
    private String describle;

    /** 图片：json数组/类似小红书 */
    @ApiModelProperty(value = "图片：json数组/类似小红书", position = 5)
    @TableField(typeHandler = SpotImageListTypeHandler.class)
    private List<SpotImageUrlPair> images;

    /** 浏览数 */
    @ApiModelProperty(value = "浏览数", position = 6)
    private Integer watchCount;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 7)
    private Integer likeCount;


}
