
package com.snowy.biz.modular.strategy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 旅游攻略表：由用户发表添加参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class StrategyAddParam {

    /** 攻略类型：交通/美食/住宿... */
    @NotBlank(message = "类型不能为空")
    @ApiModelProperty(value = "攻略类型：交通/美食/住宿...", position = 2)
    private String category;

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题", position = 3)
    private String title;

    private String userId;

    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value = "描述", position = 4)
    private String describle;

    @NotEmpty(message = "图片不能为空")
    @ApiModelProperty(value = "图片：json数组/类似小红书", position = 5)
    private List<SpotImageUrlPair> images;

    @ApiModelProperty(value = "浏览数", position = 6)
    private Integer watchCount;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 7)
    private Integer likeCount;

}
