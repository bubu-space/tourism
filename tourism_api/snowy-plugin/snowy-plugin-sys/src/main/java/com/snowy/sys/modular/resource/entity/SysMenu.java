
package com.snowy.sys.modular.resource.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonEntity;

/**
 * 菜单实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName("sys_resource")
public class SysMenu extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id", position = 2)
    private String parentId;

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 3)
    private String title;

    /** 别名 */
    @ApiModelProperty(value = "别名", position = 4)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String name;

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 5)
    private String code;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 6)
    private String category;

    /** 模块 */
    @ApiModelProperty(value = "模块", position = 7)
    private String module;

    /** 菜单类型 */
    @ApiModelProperty(value = "菜单类型", position = 8)
    private String menuType;

    /** 路径 */
    @ApiModelProperty(value = "路径", position = 9)
    private String path;

    /** 组件 */
    @ApiModelProperty(value = "组件", position = 10)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String component;

    /** 图标 */
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "图标", position = 11)
    private String icon;

    /** 颜色 */
    @ApiModelProperty(value = "颜色", position = 12)
    private String color;

    /** 是否可见 */
    @ApiModelProperty(value = "是否可见", position = 13)
    private String visible;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 14)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 15)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
