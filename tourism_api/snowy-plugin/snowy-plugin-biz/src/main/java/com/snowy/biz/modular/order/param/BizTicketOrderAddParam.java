
package com.snowy.biz.modular.order.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.snowy.biz.modular.order.entity.UserTickInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 门票订单表添加参数
 *
 * @author snowy
 *
 **/
@Data
public class BizTicketOrderAddParam {

    private String userId;

    @NotNull(message="游玩时间不能为空")
    /** 支付时间 */
    @ApiModelProperty(value = "预约时间", position = 3)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reservationTime;

    /** 支付方式 */
    @ApiModelProperty(value = "支付方式网页/手机端", position = 4)
    private String payMode;

    /** 金额 */
    @ApiModelProperty(value = "金额", position = 5)
    private BigDecimal price;

    /** 门票id */
    @ApiModelProperty(value = "门票id", position = 6)
    private String ticketId;

    @ApiModelProperty(value = "景区名称", position = 15)
    private String spotName;

    @ApiModelProperty(value = "门票名称", position = 16)
    private String ticketName;

    @NotNull(message="门票数量不能为空")
    @ApiModelProperty(value = "门票数量", position = 7)
    private Integer ticketCount;

    @NotEmpty(message="游客信息不能为空")
    @ApiModelProperty(value = "与游客门票表关联", position = 8)
    private List<UserTickInfo> userTickInfos;

    @ApiModelProperty(value = "订单状态：已支付/待完成/已完成/未评价", position = 9)
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BizTicketOrderAddParam that = (BizTicketOrderAddParam) o;
        return
                Objects.equals(userId, that.userId) &&
                Objects.equals(reservationTime, that.reservationTime) &&
                Objects.equals(payMode, that.payMode) &&
                Objects.equals(price, that.price) &&
                Objects.equals(ticketId, that.ticketId) &&
                Objects.equals(spotName, that.spotName) &&
                Objects.equals(ticketName, that.ticketName) &&
                Objects.equals(ticketCount, that.ticketCount) &&
                Objects.equals(userTickInfos, that.userTickInfos) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId,reservationTime, payMode, price, ticketId, spotName, ticketName, ticketCount, userTickInfos, status);
    }

}
