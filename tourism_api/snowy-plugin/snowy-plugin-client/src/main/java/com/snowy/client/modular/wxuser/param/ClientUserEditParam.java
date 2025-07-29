package com.snowy.client.modular.wxuser.param;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientUserEditParam {


    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private String id;

    @ApiModelProperty("姓名")
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty("头像")
    @NotNull(message = "头像不能为空")
    private String avatar;

    @ApiModelProperty("性别")
    @NotNull(message = "性别不能为空")
    private String sex;

    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty("身份证号")
    @NotNull(message = "身份证号不能为空")
    private String idCard;

}