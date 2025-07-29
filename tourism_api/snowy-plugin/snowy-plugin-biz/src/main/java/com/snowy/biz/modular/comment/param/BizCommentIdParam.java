
package com.snowy.biz.modular.comment.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 评价留言表Id参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizCommentIdParam {

    /** id */
    @ApiModelProperty(value = "id", required = true)
    @NotBlank(message = "id不能为空")
    private String id;
}
