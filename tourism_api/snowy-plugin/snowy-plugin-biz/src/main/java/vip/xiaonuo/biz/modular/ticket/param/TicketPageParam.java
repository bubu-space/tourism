
package vip.xiaonuo.biz.modular.ticket.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 景点门票表查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class TicketPageParam {

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


    /** 门票名称 */
    @ApiModelProperty(value = "门票名称")
    private String name;

    @ApiModelProperty("景区id")
    private String spotId;

}
