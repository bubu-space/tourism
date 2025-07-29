
package com.snowy.biz.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表编辑参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizUserEditParam {

    /** ID */
    @ApiModelProperty(value = "ID", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 用户名 */
    @ApiModelProperty(value = "用户名", position = 2)
    private String name;

    /** 手机号 */
    @ApiModelProperty(value = "手机号", position = 3)
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
