package com.snowy.client.modular.wxuser.param;

import com.alibaba.druid.sql.visitor.functions.Insert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ClientUserAddParam {

    @ApiModelProperty("姓名")
    @NotNull(message = "姓名不能为空")
    private String name;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号码不能为空")
    //使用 @Pattern 注解，通过正则表达式 ^1[3-9]\\d{9}$ 验证手机号格式，若不符合则抛出 message 中的错误提示。
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式有误")
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

    @ApiModelProperty(value = "用户身份证", position = 7)
    @NotBlank(message = "身份证号不能为空")
    //^ 和 $：匹配字符串的开始和结束，确保整个字符串符合规则。
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)", message = "身份证号格式有误")
    private String idCard;//使用@Pattern注解，通过正则表达式（regex）定义身份证号的合法格式，若不符合则抛出message中的错误提示。

}
