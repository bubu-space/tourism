package vip.xiaonuo.biz.modular.order.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class  UserTickInfo {
    @ApiModelProperty(value = "游客姓名", position = 1)
    private String userName;
    @ApiModelProperty(value = "游客手机号", position = 2)
    private String userPhone;
    @ApiModelProperty(value = "游客身份证号", position = 3)
    private String userIdCard;
}
