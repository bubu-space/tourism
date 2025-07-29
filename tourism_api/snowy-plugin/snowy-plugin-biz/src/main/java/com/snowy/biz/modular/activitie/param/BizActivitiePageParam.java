
package com.snowy.biz.modular.activitie.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景区/景点活动表查询参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizActivitiePageParam {

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

    /** 所属景点 */
    @ApiModelProperty(value = "所属景点")
    private String spotId;

    /** 活动标题 */
    @ApiModelProperty(value = "活动标题")
    private String title;

}
