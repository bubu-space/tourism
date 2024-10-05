
package vip.xiaonuo.dev.modular.job.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 定时任务Id参数
 *
 * @author gtc
 * 
 */
@Getter
@Setter
public class DevJobIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
