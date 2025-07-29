
package com.snowy.dev.modular.config.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 配置添加参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevConfigAddParam {

    /** 配置键 */
    @ApiModelProperty(value = "配置键", required = true, position = 1)
    @NotBlank(message = "configKey不能为空")
    private String configKey;

    /** 配置值 */
    @ApiModelProperty(value = "配置值", required = true, position = 2)
    @NotBlank(message = "configValue不能为空")
    private String configValue;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 3)
    private String remark;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 4)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 5)
    private String extJson;
}
