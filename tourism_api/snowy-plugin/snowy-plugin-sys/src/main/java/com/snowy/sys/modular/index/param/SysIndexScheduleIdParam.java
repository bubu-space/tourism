
package com.snowy.sys.modular.index.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 日程Id参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class SysIndexScheduleIdParam {

    /** 日程id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
