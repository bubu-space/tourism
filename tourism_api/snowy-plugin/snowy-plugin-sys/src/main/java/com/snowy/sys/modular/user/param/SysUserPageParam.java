
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户查询参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserPageParam {

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

    /** 账号、姓名关键词 */
    @ApiModelProperty(value = "账号、姓名关键词")
    private String searchKey;

    /** 用户状态 */
    @ApiModelProperty(value = "用户状态")
    private String userStatus;

    /** 所属组织 */
    @ApiModelProperty(value = "所属组织")
    private String orgId;
}
