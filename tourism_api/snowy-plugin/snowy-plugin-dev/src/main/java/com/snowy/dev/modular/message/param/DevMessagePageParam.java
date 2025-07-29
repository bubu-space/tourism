
package com.snowy.dev.modular.message.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 站内信查询参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class DevMessagePageParam {

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

    /** 站内信分类 */
    @ApiModelProperty(value = "站内信分类")
    private String category;

    /** 主题关键词 */
    @ApiModelProperty(value = "主题关键词")
    private String searchKey;

    /** 接收用户id */
    @ApiModelProperty(value = "接收用户id")
    private String receiveUserId;
}
