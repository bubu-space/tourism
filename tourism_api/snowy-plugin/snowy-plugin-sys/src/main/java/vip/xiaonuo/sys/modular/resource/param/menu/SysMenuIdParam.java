
package vip.xiaonuo.sys.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 菜单Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysMenuIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
