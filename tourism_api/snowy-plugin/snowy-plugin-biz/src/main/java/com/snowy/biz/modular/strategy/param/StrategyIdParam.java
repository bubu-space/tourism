
package com.snowy.biz.modular.strategy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 旅游攻略表：由用户发表Id参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class StrategyIdParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
