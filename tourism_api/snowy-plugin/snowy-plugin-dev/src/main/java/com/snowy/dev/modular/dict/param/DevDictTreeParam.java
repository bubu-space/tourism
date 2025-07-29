
package com.snowy.dev.modular.dict.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典树参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevDictTreeParam {

    /** 字典分类 */
    @ApiModelProperty(value = "字典分类")
    private String category;
}
