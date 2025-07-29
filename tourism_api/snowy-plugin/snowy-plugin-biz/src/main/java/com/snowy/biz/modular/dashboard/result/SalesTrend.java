package com.snowy.biz.modular.dashboard.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class SalesTrend {
    @ApiModelProperty("日期")
    private LocalDate date;
    @ApiModelProperty("门票数量")
    private Integer ticketcount;
    @ApiModelProperty("门票价格")
    private BigDecimal ticketprice;


}
