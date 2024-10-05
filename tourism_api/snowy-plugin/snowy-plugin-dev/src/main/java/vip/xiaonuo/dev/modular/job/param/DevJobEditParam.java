
package vip.xiaonuo.dev.modular.job.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 定时任务编辑参数
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevJobEditParam {

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

    /** 任务类名 */
    @ApiModelProperty(value = "任务类名", required = true, position = 4)
    @NotBlank(message = "actionClass不能为空")
    private String actionClass;

    /** cron表达式 */
    @ApiModelProperty(value = "cron表达式", required = true, position = 5)
    @NotBlank(message = "cronExpression不能为空")
    private String cronExpression;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 6)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 7)
    private String extJson;
}
