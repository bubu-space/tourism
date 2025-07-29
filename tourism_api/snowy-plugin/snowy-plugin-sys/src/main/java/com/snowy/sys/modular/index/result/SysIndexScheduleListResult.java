
package com.snowy.sys.modular.index.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 日程列表结果
 *
 * @author snowy
 *
 */
@Getter
@Setter
public class SysIndexScheduleListResult {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 2)
    private String scheduleUserId;

    /** 用户姓名 */
    @ApiModelProperty(value = "用户姓名", position = 3)
    private String scheduleUserName;

    /** 日程日期 */
    @ApiModelProperty(value = "日程日期", position = 4)
    private String scheduleDate;

    /** 日程时间 */
    @ApiModelProperty(value = "日程时间", position = 5)
    private String scheduleTime;

    /** 日程内容 */
    @ApiModelProperty(value = "日程内容", position = 6)
    private String scheduleContent;
}
