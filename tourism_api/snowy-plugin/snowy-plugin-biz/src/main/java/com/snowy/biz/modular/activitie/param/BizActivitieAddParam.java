
package com.snowy.biz.modular.activitie.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景区/景点活动表添加参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizActivitieAddParam {

    /** 所属景点 */
    @ApiModelProperty(value = "所属景点", position = 2)
    private String spotId;

    /** 活动标题 */
    @ApiModelProperty(value = "活动标题", position = 3)
    private String title;

    /** 图片 */
    @ApiModelProperty(value = "图片", position = 4)
    private SpotImageUrlPair image;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 5)
    private String content;

    /** VIEW_COUNT */
    @ApiModelProperty(value = "VIEW_COUNT", position = 11)
    private Integer viewCount;

}
