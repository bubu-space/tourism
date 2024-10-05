package vip.xiaonuo.biz.modular.dashboard.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class WeekViewId {
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
