
package vip.xiaonuo.biz.modular.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName("biz_user")
public class BizUser {

    /** ID */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID", position = 1)
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

    /** DELETE_FLAG */
    @ApiModelProperty(value = "DELETE_FLAG", position = 10)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** CREATE_USER */
    @ApiModelProperty(value = "CREATE_USER", position = 11)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** CREATE_TIME */
    @ApiModelProperty(value = "CREATE_TIME", position = 12)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** UPDATE_USER */
    @ApiModelProperty(value = "UPDATE_USER", position = 13)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** UPDATE_TIME */
    @ApiModelProperty(value = "UPDATE_TIME", position = 14)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
