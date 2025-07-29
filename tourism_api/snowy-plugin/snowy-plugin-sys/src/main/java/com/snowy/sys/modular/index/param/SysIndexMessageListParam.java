
package com.snowy.sys.modular.index.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 站内信列表参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysIndexMessageListParam {

    /** 条数" */
    @ApiModelProperty(value = "条数")
    private Integer limit;
}
