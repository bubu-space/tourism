
package com.snowy.sys.modular.position.enums;

import lombok.Getter;
import com.snowy.common.exception.CommonException;

/**
 * 职位分类枚举
 *
 * @author snowy
 *
 **/
@Getter
public enum SysPositionCategoryEnum {

    /** 高层 */
    HIGH("HIGH"),

    /** 中层 */
    MIDDLE("MIDDLE"),

    /** 基层 */
    LOW("LOW");

    private final String value;

    SysPositionCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = HIGH.getValue().equals(value) || MIDDLE.getValue().equals(value) || LOW.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的职位分类：{}", value);
        }
    }
}
