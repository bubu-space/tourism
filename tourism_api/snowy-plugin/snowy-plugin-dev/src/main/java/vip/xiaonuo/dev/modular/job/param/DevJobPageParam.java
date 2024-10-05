
package vip.xiaonuo.dev.modular.job.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务查询参数
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevJobPageParam {

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

    /** 任务分类 */
    @ApiModelProperty(value = "任务分类")
    private String category;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;

    /** 任务状态 */
    @ApiModelProperty(value = "任务状态")
    private String jobStatus;
}
