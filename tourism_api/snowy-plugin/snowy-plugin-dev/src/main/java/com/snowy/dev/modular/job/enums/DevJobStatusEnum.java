
package com.snowy.dev.modular.job.enums;

import lombok.Getter;
import com.snowy.common.exception.CommonException;

/**
 * 定时任务状态枚举
 *
 * @author snowy
 *
 */
@Getter
public enum DevJobStatusEnum {

    /**
     * 运行
     */
    RUNNING("RUNNING"),

    /**
     * 停止
     */
    STOPPED("STOPPED");

    private final String value;

    DevJobStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = RUNNING.getValue().equals(value) || STOPPED.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的定时任务状态：{}", value);
        }
    }
}
