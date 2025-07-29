package com.snowy.client.modular.wxindex.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RedStrategy {
    @ApiModelProperty(value = "攻略ID", position = 1)
    private String strategyId;
    @ApiModelProperty(value = "攻略标题", position = 2)
    private String title;
}
