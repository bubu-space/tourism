
package com.snowy.biz.modular.user.param;

import com.alibaba.druid.sql.visitor.functions.Insert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表添加参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class BizUserAddParam {

    /** 用户名 */
    @ApiModelProperty(value = "用户名", position = 2)
    private String name;

    /** 手机号 */
    @ApiModelProperty(value = "手机号", position = 3)
    @NotBlank(message = "手机号码不能为空", groups = {Insert.class})
    private String phone;

    /** 密码 */
    @ApiModelProperty(value = "密码", position = 4)
    private String password;

    /** 用户头像 */
    @ApiModelProperty(value = "用户头像", position = 5)
    private String avatar;

    /** 用户状态 */
    @ApiModelProperty(value = "用户状态", position = 6)
    private String status;

    /** 用户身份证 */
    @ApiModelProperty(value = "用户身份证", position = 7)
    private String idCard;

    /** 性别 */
    @ApiModelProperty(value = "性别", position = 8)
    private String sex;

    /** 年龄 */
    @ApiModelProperty(value = "年龄", position = 9)
    private Integer age;

}
