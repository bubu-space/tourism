
package vip.xiaonuo.biz.modular.activitie.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 景区/景点活动表Id参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizActivitieIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
