
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 编辑个人信息参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserUpdateInfoParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 姓名 */
    @ApiModelProperty(value = "姓名", required = true, position = 2)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 手机 */
    @ApiModelProperty(value = "手机", position = 3)
    private String phone;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", position = 4)
    private String nickname;

    /** 性别 */
    @ApiModelProperty(value = "性别", position = 5)
    private String gender;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期", position = 6)
    private String birthday;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", position = 7)
    private String email;

    /** 签名 */
    @ApiModelProperty(value = "签名，图片base64", position = 8)
    private String signature;
}
