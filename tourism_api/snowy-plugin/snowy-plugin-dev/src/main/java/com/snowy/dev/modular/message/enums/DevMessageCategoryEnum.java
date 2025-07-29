
package com.snowy.dev.modular.message.enums;

import lombok.Getter;

/**
 * 分类枚举
 *
 * @author snowy
 *
 **/
@Getter
public enum DevMessageCategoryEnum {

    /** 系统 */
    SYS("SYS"),

    /** 业务 */
    BIZ("BIZ");

    private final String value;

    DevMessageCategoryEnum(String value) {
        this.value = value;
    }
}
