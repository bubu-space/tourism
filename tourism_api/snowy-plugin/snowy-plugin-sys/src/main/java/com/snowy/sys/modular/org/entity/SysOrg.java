
package com.snowy.sys.modular.org.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonEntity;
import com.snowy.sys.modular.user.entity.SysUser;

/**
 * 组织实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName("sys_org")
public class SysOrg extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id", position = 2)
    private String parentId;

    /** 主管id */
    @ApiModelProperty(value = "主管id", position = 3)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @Trans(type = TransType.SIMPLE, target = SysUser.class, fields = "name", alias = "director", ref = "directorName")
    private String directorId;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 4)
    private String name;

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 5)
    private String code;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 6)
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 7)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 8)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
