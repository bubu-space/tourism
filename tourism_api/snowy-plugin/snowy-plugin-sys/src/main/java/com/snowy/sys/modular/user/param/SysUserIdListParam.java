
package com.snowy.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户id集合参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class SysUserIdListParam {

    /** id集合 */
    @ApiModelProperty(value = "id集合", required = true)
    @NotNull(message = "idList不能为空")
    private List<String> idList;
}
