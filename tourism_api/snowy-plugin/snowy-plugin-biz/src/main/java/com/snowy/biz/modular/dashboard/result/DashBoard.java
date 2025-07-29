package com.snowy.biz.modular.dashboard.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DashBoard {
    @ApiModelProperty(value = "当日售票数", position = 1)
    private Integer DayTitckCount;
    @ApiModelProperty(value = "当日售票总额", position = 2)
    private BigDecimal DayTotalPrice;
    @ApiModelProperty(value = "当周售票数", position = 3)
    private Integer WeekTitckCount;
    @ApiModelProperty(value = "当周售票总额", position = 4)
    private BigDecimal WeekTotalPrice;
    @ApiModelProperty(value = "累计售票数", position = 5)
    private Integer TitckCount;
    @ApiModelProperty(value = "累计售票总额", position = 6)
    private BigDecimal TotalPrice;
}
