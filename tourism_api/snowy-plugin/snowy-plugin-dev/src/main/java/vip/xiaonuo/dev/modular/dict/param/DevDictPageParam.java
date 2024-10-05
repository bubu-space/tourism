
package vip.xiaonuo.dev.modular.dict.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典查询参数
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevDictPageParam {

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

    /** 父id */
    @ApiModelProperty(value = "父id")
    private String parentId;

    /** 字典分类 */
    @ApiModelProperty(value = "字典分类")
    private String category;

    /** 字典文字关键词 */
    @ApiModelProperty(value = "字典文字关键词")
    private String searchKey;
}
