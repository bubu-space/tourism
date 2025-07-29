
package com.snowy.common.enums;

import lombok.Getter;
import com.snowy.common.exception.CommonException;

/**
 * 通用排序方式枚举
 *
 * @author snowy
 *
 **/
@Getter
public enum CommonSortOrderEnum {

    /** 升序 */
    ASC("ASCEND"),

    /** 降序 */
    DESC("DESCEND");

    private final String value;

    CommonSortOrderEnum(String value) {
        this.value = value.toUpperCase();
    }

    public static void validate(String value) {
        boolean flag = ASC.getValue().toLowerCase().equals(value) || DESC.getValue().toLowerCase().equals(value);
        if(!flag) {
            throw new CommonException("不支持该排序方式：{}", value);
        }
    }
}
