
package vip.xiaonuo.sys.modular.role.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 角色编辑参数
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class SysRoleEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 2)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 3)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 4)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 5)
    private String orgId;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 6)
    private String extJson;
}
