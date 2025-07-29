
package com.snowy.dev.modular.sms.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 短信查询参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevSmsPageParam {

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

    /** 短信引擎 */
    @ApiModelProperty(value = "短信引擎")
    private String engine;

    /** 手机号关键词 */
    @ApiModelProperty(value = "手机号关键词")
    private String searchKey;
}
