
package vip.xiaonuo.sys.modular.position.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 职位Id参数
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class SysPositionIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
