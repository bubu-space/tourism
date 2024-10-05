
package vip.xiaonuo.biz.modular.ticket.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 景点门票表Id参数
 *
 * @author gtc
 *
 **/
@Data
public class TicketIdParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
