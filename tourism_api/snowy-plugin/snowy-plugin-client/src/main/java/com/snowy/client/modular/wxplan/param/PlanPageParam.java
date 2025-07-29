package com.snowy.client.modular.wxplan.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlanPageParam {
    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 排序方式 */
    @ApiModelProperty(value = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

    /** 名称关键词 */
    @ApiModelProperty(value = "状态已完成/未完成")
    private String status;
}
