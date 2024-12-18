
package vip.xiaonuo.biz.modular.order.enums;

import lombok.Getter;

/**
 * 门票订单表枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum BizTicketOrderEnum {

    /** 测试 */
    TEST("TEST");

    private final String value;

    BizTicketOrderEnum(String value) {
        this.value = value;
    }
}
