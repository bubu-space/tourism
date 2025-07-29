
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 编辑个人工作台参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserUpdateWorkbenchParam {

    /** 工作台数据 */
    @ApiModelProperty(value = "工作台数据", required = true)
    @NotBlank(message = "workbenchData不能为空")
    private String workbenchData;
}
