
package com.snowy.dev.modular.job.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 定时任务添加参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class DevJobAddParam {

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 1)
    @NotBlank(message = "name不能为空")
    private String name;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 2)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 任务类名 */
    @ApiModelProperty(value = "任务类名", required = true, position = 3)
    @NotBlank(message = "actionClass不能为空")
    private String actionClass;

    /** cron表达式 */
    @ApiModelProperty(value = "cron表达式", required = true, position = 4)
    @NotBlank(message = "cronExpression不能为空")
    private String cronExpression;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 5)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 6)
    private String extJson;
}
