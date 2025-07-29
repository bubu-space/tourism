
package com.snowy.sys.modular.role.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色添加参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class SysRoleAddParam {

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 1)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 2)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 3)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 4)
    private String orgId;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 5)
    private String extJson;
}
