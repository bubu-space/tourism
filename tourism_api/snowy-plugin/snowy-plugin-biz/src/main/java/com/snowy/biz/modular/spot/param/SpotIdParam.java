
package com.snowy.biz.modular.spot.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 景点表Id参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class SpotIdParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "spotId不能为空")
    private String id;
}
