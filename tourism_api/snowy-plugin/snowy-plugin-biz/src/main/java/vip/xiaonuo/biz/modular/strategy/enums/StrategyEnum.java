
package vip.xiaonuo.biz.modular.strategy.enums;

import lombok.Getter;

/**
 * 旅游攻略表：由用户发表枚举
 *
 * @author gtc
 * 
 **/
@Getter
public enum StrategyEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    StrategyEnum(String value) {
        this.value = value;
    }
}
