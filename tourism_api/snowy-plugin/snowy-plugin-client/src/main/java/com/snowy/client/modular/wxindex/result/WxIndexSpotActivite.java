package com.snowy.client.modular.wxindex.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

@Data
public class WxIndexSpotActivite {
    @ApiModelProperty(value = "活动id", position = 1)
    private String id;

    /** 所属景点 */
    @ApiModelProperty(value = "所属景点", position = 2)
    private String spotname;

    /** 活动标题 */
    @ApiModelProperty(value = "活动标题", position = 3)
    private String title;

    /** 图片 */
    @ApiModelProperty(value = "图片", position = 4)
    private SpotImageUrlPair image;

    /** 内容 */
    @ApiModelProperty(value = "浏览量", position = 5)
    private String viewCount;

    @ApiModelProperty(value = "创建时间", position = 6)
    private String createTime;
}
