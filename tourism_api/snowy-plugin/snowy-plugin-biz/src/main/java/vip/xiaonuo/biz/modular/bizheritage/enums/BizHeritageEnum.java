
package vip.xiaonuo.biz.modular.bizheritage.enums;

import lombok.Getter;

/**
 * 历史遗产动态表枚举
 *
 * @author gtc
 * 
 **/
@Getter
public enum BizHeritageEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizHeritageEnum(String value) {
        this.value = value;
    }
}
