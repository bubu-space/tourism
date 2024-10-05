
package vip.xiaonuo.biz.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户表Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizUserIdParam {

    /** ID */
    @ApiModelProperty(value = "ID", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
