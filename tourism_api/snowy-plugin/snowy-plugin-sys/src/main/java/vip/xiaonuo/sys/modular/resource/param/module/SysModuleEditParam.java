
package vip.xiaonuo.sys.modular.resource.param.module;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 模块编辑参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysModuleEditParam {

    /** id */
    @ApiModelProperty(value = "id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 标题 */
    @ApiModelProperty(value = "标题", required = true, position = 2)
    @NotBlank(message = "title不能为空")
    private String title;

    /** 图标 */
    @ApiModelProperty(value = "图标", required = true, position = 3)
    @NotBlank(message = "icon不能为空")
    private String icon;

    /** 颜色 */
    @ApiModelProperty(value = "颜色", required = true, position = 4)
    @NotBlank(message = "color不能为空")
    private String color;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 5)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 6)
    private String extJson;
}
