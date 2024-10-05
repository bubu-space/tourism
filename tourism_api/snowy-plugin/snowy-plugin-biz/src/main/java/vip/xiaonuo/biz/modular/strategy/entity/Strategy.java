
package vip.xiaonuo.biz.modular.strategy.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vip.xiaonuo.biz.modular.spot.converter.SpotImageListTypeHandler;
import vip.xiaonuo.biz.modular.spot.entity.SpotImageUrlPair;

import java.util.Date;
import java.util.List;

/**
 * 旅游攻略表：由用户发表实体
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
@TableName(value = "biz_strategy", autoResultMap = true)
public class Strategy {

    /** 主键 */
    @TableId
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    /** 攻略类型：交通/美食/住宿... */
    @ApiModelProperty(value = "攻略类型：交通/美食/住宿...", position = 2)
    private String category;

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

    /** DELETE_FLAG */
    @ApiModelProperty(value = "DELETE_FLAG", position = 9)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** CREATE_USER */
    @ApiModelProperty(value = "CREATE_USER", position = 10)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** CREATE_TIME */
    @ApiModelProperty(value = "CREATE_TIME", position = 11)
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** UPDATE_USER */
    @ApiModelProperty(value = "UPDATE_USER", position = 12)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** UPDATE_TIME */
    @ApiModelProperty(value = "UPDATE_TIME", position = 13)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
