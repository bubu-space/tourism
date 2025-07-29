
package com.snowy.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     通用基础字段实体：创建时间、创建人、修改时间、修改人，需要此通用字段的实体可继承此类，
 *     继承此类要求数据表有对应的字段
 * </p>
 */
@Getter
@Setter
public class CommonEntity implements Serializable, TransPojo {

    /** 删除标志 */
    @JsonIgnore
    @TableLogic
    @ApiModelProperty(value = "删除标志", position = 999)
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 1000)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 1001)
    @TableField(fill = FieldFill.INSERT)
    @Trans(type = TransType.RPC, targetClassName = "com.snowy.sys.modular.user.entity.SysUser", fields = "name", alias = "createUser", ref = "createUserName")
    private String createUser;


    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 1003)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 1004)
    @TableField(fill = FieldFill.UPDATE)
    @Trans(type = TransType.RPC, targetClassName = "com.snowy.sys.modular.user.entity.SysUser", fields = "name", alias = "updateUser", ref = "updateUserName")
    private String updateUser;

}
