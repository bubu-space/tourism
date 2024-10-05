
package vip.xiaonuo.biz.modular.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 门票订单表编辑参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizTicketOrderEditParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 2)
    private String userId;

    /** 支付时间 */
    @ApiModelProperty(value = "预约时间", position = 3)
    private Date reservationTime;

    /** 支付方式 */
    @ApiModelProperty(value = "支付方式", position = 4)
    private String payMode;

    /** 金额 */
    @ApiModelProperty(value = "金额", position = 5)
    private BigDecimal price;

    /** 门票id */
    @ApiModelProperty(value = "门票id", position = 6)
    private String ticketId;

    /** 门票数量 */
    @ApiModelProperty(value = "门票数量", position = 7)
    private Integer ticketCount;

    /** 游客信息：json数组对象存储，长度与门票数量对应 */
    @ApiModelProperty(value = "游客信息：json数组对象存储，长度与门票数量对应", position = 8)
    private String userInfos;

    /** 订单状态：待支付/已支付/待完成/已完成/未评价 */
    @ApiModelProperty(value = "订单状态：待支付/已支付/待完成/已完成/未评价", position = 9)
    private String status;

}
