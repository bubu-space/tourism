
package com.snowy.biz.modular.spot.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 景点表添加参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SpotAddParam {


    /** 景区名称 */
    @ApiModelProperty(value = "景区名称", position = 4)
    private String spotName;

    /** 经纬度 */
    @ApiModelProperty(value = "经纬度", position = 5)
    private Double longitude;

    /** 经纬度 */
    @ApiModelProperty(value = "经纬度", position = 6)
    private Double latitude;

    /** 景点所在地点 */
    @ApiModelProperty(value = "景点所在地点", position = 7)
    private String address;


    /** 经营时间 */
    @ApiModelProperty(value = "经营时间", position = 9)
    private String openingTime;


    /** 景点描述 */
    @ApiModelProperty(value = "景点描述", position = 11)
    private String description;


    /** 景区类型 */
    @ApiModelProperty(value = "景区标签", position = 13)
    private List<String> spotTags;

    /** 是否可以预约：allow/notallow */
    @ApiModelProperty(value = "是否可以预约：allow/notallow", position = 14)
    private String isPrebook;

    /** 景区官方网站 */
    @ApiModelProperty(value = "景区官方网站", position = 15)
    private String websiteUrl;

    /** 景区联系电话 */
    @ApiModelProperty(value = "景区联系电话", position = 16)
    private String contactPhone;

    @ApiModelProperty(value = "景区封面", position = 17)
    private SpotImageUrlPair cover;

    /** 景区代表性图片 */
    @ApiModelProperty(value = "景区代表性图片", position = 17)
    private List<SpotImageUrlPair> images;

    /** 宣传视频 */
    @ApiModelProperty(value = "宣传视频", position = 18)
    private List<SpotImageUrlPair>  video;

    /** 评分 */
    @ApiModelProperty(value = "评分", position = 19)
    private BigDecimal rate;

    /** 景区地图/避免与关键字重合，使用plat字段 */
    @ApiModelProperty(value = "景区地图/避免与关键字重合，使用plat字段", position = 20)
    private SpotImageUrlPair plat;

    /** 星级 */
    @ApiModelProperty(value = "星级", position = 21)
    private Integer startLevel;

    /** 交通信息：json格式 */
    @ApiModelProperty(value = "交通信息：json格式", position = 22)
    private String traffice;


    /** 评论数 */
    @ApiModelProperty(value = "评论数", position = 24)
    private Integer commentNum;

    /** 额外信息 */
    @ApiModelProperty(value = "额外信息", position = 25)
    private String extJson;

}
