
package vip.xiaonuo.sys.modular.position.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 职位编辑参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysPositionEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 组织id */
    @ApiModelProperty(value = "组织id", required = true, position = 2)
    @NotBlank(message = "orgId不能为空")
    private String orgId;

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 3)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 4)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 5)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 6)
    private String extJson;
}
