
package com.snowy.sys.modular.org.enums;

import lombok.Getter;
import com.snowy.common.exception.CommonException;

/**
 * 组织分类枚举
 *
 * @author snowy
 *
 **/
@Getter
public enum SysOrgCategoryEnum {

    /** 公司 */
    COMPANY("COMPANY"),

    /** 部门 */
    DEPT("DEPT");

    private final String value;

    SysOrgCategoryEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = COMPANY.getValue().equals(value) || DEPT.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的组织分类：{}", value);
        }
    }
}
