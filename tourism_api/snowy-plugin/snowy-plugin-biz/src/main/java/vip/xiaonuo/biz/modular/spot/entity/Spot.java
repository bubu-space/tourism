
package vip.xiaonuo.biz.modular.spot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import vip.xiaonuo.biz.modular.spot.converter.SpotImageListTypeHandler;
import vip.xiaonuo.biz.modular.spot.converter.SpotImageTypeHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 景点表实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName(value = "biz_spot", autoResultMap = true)
public class Spot {

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键", position = 1)
    private String id;


    /** 景区名称 */
    @ApiModelProperty(value = "景区名称", position = 4)
    private String spotName;

    /** 经纬度 */
    @ApiModelProperty(value = "经纬度", position = 5)
    private double longitude;

    /** 经纬度 */
    @ApiModelProperty(value = "经纬度", position = 6)
    private double latitude;

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
    @TableField(typeHandler = JacksonTypeHandler.class)
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
    @TableField(value = "cover", typeHandler = SpotImageTypeHandler.class)
    private SpotImageUrlPair cover;

    /** 景区代表性图片 */
    @ApiModelProperty(value = "景区代表性图片", position = 17)
    @TableField(value = "images", typeHandler = SpotImageListTypeHandler.class)
    private List<SpotImageUrlPair> images;

    /** 宣传视频 */
    @TableField(value = "video", typeHandler = SpotImageListTypeHandler.class)
    @ApiModelProperty(value = "宣传视频", position = 18)
    private List<SpotImageUrlPair> video;

    /** 评分 */
    @ApiModelProperty(value = "评分", position = 19)
    private BigDecimal rate;

    /** 景区地图/避免与关键字重合，使用plat字段 */

    @ApiModelProperty(value = "景区地图/避免与关键字重合，使用plat字段", position = 20)
    @TableField(value = "plat", typeHandler = SpotImageTypeHandler.class)
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

    /** 删除标志 */
    @ApiModelProperty(value = "删除标志", position = 26)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 27)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 28)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 29)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 30)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** 门票价格 */
    @TableField(exist = false)
    @ApiModelProperty(value = "门票价格", position = 31)
    private BigDecimal minPrice;


}
