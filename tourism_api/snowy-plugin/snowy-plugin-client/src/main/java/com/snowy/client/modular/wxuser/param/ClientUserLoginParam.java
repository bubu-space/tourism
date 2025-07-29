package com.snowy.client.modular.wxuser.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientUserLoginParam {

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
