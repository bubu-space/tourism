
package vip.xiaonuo.biz.modular.spot.enums;

import lombok.Getter;

/**
 * 景点表枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum SpotEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    SpotEnum(String value) {
        this.value = value;
    }
}
