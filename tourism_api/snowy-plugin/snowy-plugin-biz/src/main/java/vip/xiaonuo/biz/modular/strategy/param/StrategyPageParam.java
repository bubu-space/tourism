
package vip.xiaonuo.biz.modular.strategy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 旅游攻略表：由用户发表查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class StrategyPageParam {

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


    /** 攻略类型：交通/美食/住宿... */
    @ApiModelProperty(value = "攻略类型：交通/美食/住宿...")
    private String category;

    /** 标题 */
    @ApiModelProperty(value = "标题")
    private String title;


}
