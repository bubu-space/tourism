
package vip.xiaonuo.biz.modular.bizrecommend.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 住/礼/食推荐表Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizRecommendIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
