
package vip.xiaonuo.dev.modular.dict.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 字典Id参数
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevDictIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
