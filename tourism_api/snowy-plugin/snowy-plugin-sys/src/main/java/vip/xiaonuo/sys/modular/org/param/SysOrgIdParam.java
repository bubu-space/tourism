
package vip.xiaonuo.sys.modular.org.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 组织Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysOrgIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
