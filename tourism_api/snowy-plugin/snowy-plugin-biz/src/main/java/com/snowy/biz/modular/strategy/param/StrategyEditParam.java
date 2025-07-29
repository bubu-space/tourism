
package com.snowy.biz.modular.strategy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.spot.entity.SpotImageUrlPair;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 旅游攻略表：由用户发表编辑参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class StrategyEditParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotBlank(message = "id不能为空")
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
    private List<SpotImageUrlPair> images;

    /** 浏览数 */
    @ApiModelProperty(value = "浏览数", position = 6)
    private Integer watchCount;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 7)
    private Integer likeCount;

}
