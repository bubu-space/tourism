
package vip.xiaonuo.biz.modular.activitie.enums;

import lombok.Getter;

/**
 * 景区/景点活动表枚举
 *
 * @author gtc
 * 
 **/
@Getter
public enum BizActivitieEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizActivitieEnum(String value) {
        this.value = value;
    }
}
