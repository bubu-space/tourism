
package com.snowy.sys.modular.resource.param.button;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 按钮添加参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class SysButtonAddParam {

    /** 父id */
    @ApiModelProperty(value = "父id", required = true, position = 1)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 标题 */
    @ApiModelProperty(value = "标题", required = true, position = 2)
    @NotBlank(message = "title不能为空")
    private String title;

    /** 编码 */
    @ApiModelProperty(value = "编码", required = true, position = 3)
    @NotBlank(message = "code不能为空")
    private String code;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 4)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 5)
    private String extJson;
}
