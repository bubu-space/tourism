
package com.snowy.biz.modular.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 门票订单表查询参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizTicketOrderPageParam {

    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 排序字段 */
    @ApiModelProperty(value = "排序字段，字段驼峰名称，如：userName")
    private String sortField;

    /** 排序方式 */
    @ApiModelProperty(value = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

    /** 关键词 */
    @ApiModelProperty(value = "关键词")
    private String searchKey;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /** 订单状态：待支付/已支付/待完成/已完成/未评价 */
    @ApiModelProperty(value = "订单状态：待支付/已支付/待完成/已完成/未评价")
    private String status;

}
