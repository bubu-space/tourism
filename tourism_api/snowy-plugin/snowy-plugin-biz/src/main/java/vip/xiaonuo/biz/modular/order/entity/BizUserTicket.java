package vip.xiaonuo.biz.modular.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@TableName("biz_user_ticket")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BizUserTicket {
    @ApiModelProperty(value = "主键", position = 2)
    private String id;
    @ApiModelProperty(value = "门票id", position = 4)
    private String ticketId;
    @ApiModelProperty(value = "订单id", position = 5)
    private String orderId;
    @ApiModelProperty(value = "游客姓名", position = 6)
    private String userName;
    @ApiModelProperty(value = "游客手机号", position = 7)
    private String userPhone;
    @ApiModelProperty(value = "游客身份证号", position = 8)
    private String userIdCard;
    @ApiModelProperty(value = "检票码", position = 9)
    private String ticketCode;
    @ApiModelProperty(value = "状态", position = 10)
    private String ticketStatus;

}
