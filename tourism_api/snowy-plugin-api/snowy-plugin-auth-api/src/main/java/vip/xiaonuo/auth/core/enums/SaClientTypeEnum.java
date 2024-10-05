
package vip.xiaonuo.auth.core.enums;

import lombok.Getter;
import vip.xiaonuo.common.exception.CommonException;

/**
 * 登录端类型枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum SaClientTypeEnum {

    /**
     * B端用户
     */
    B("B"),

    /**
     * C端用户
     */
    C("C");

    private final String value;

    SaClientTypeEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = B.getValue().equals(value) || C.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的登录端类型：{}", value);
        }
    }
}
