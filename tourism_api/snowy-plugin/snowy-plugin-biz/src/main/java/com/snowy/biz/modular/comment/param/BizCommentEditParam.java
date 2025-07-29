
package com.snowy.biz.modular.comment.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评价留言表编辑参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizCommentEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 2)
    private String userId;

    /** 订单id */
    @ApiModelProperty(value = "订单id", position = 3)
    private String orderId;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 4)
    private String content;

    /** 点赞数 */
    @ApiModelProperty(value = "点赞数", position = 5)
    private Integer likeCount;

    /** 打分 */
    @ApiModelProperty(value = "打分", position = 6)
    private Double score;

    private Boolean audit;

}
