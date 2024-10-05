
package vip.xiaonuo.biz.modular.bizheritage.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 历史遗产动态表查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class BizHeritagePageParam {

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

    /** 遗产名称 */
    @ApiModelProperty(value = "遗产名称")
    private String title;

    /** 遗产类型 */
    @ApiModelProperty(value = "遗产类型")
    private String category;



}
