
package vip.xiaonuo.biz.modular.bizrecommend.enums;

import lombok.Getter;

/**
 * 住/礼/食推荐表枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum BizRecommendEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizRecommendEnum(String value) {
        this.value = value;
    }
}
