
package vip.xiaonuo.biz.modular.comment.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 评价留言表查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizCommentPageParam {

    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;


    /** 景区id */
    @ApiModelProperty(value = "景区id")
    private String spotId;

    /** 订单id */
    @ApiModelProperty(value = "订单id")
    private String orderId;

}
