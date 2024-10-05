
package vip.xiaonuo.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysUserIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
