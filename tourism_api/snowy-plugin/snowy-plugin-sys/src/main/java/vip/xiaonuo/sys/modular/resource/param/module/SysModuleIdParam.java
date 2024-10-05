
package vip.xiaonuo.sys.modular.resource.param.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 模块Id参数
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class SysModuleIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
