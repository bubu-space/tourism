
package vip.xiaonuo.biz.modular.bizheritage.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 历史遗产动态表Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizHeritageIdParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
