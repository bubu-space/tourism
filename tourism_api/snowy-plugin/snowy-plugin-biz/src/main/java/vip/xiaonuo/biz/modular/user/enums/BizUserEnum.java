
package vip.xiaonuo.biz.modular.user.enums;

import lombok.Getter;

/**
 * 用户表枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum BizUserEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizUserEnum(String value) {
        this.value = value;
    }
}
