
package com.snowy.sys.modular.index.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 日程添加参数
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class SysIndexScheduleAddParam {

    /** 日程日期 */
    @ApiModelProperty(value = "日程日期", required = true, position = 1)
    @NotBlank(message = "scheduleDate不能为空")
    private String scheduleDate;

    /** 日程时间 */
    @ApiModelProperty(value = "日程时间", required = true, position = 2)
    @NotBlank(message = "scheduleTime不能为空")
    private String scheduleTime;

    /** 日程内容 */
    @ApiModelProperty(value = "日程内容", required = true, position = 3)
    @NotBlank(message = "scheduleContent不能为空")
    private String scheduleContent;

    /** 用户id */
    @ApiModelProperty(value = "用户id", hidden = true, position = 4)
    private String scheduleUserId;

    /** 用户姓名 */
    @ApiModelProperty(value = "用户姓名", hidden = true, position = 5)
    private String scheduleUserName;
}
