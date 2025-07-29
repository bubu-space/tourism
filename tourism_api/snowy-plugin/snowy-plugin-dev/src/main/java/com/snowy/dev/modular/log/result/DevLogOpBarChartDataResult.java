
package com.snowy.dev.modular.log.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 操作日志柱状图数据结果
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevLogOpBarChartDataResult {

    /** 日期 */
    @ApiModelProperty(value = "日期", position = 1)
    private String date;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    /** 数量 */
    @ApiModelProperty(value = "数量", position = 3)
    private Long count;
}
