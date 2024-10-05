
package vip.xiaonuo.auth.modular.login.enums;

import lombok.Getter;
import vip.xiaonuo.common.exception.CommonException;

/**
 * 登录设备类型枚举
 *
 * @author gtc
 *
 **/
@Getter
public enum AuthDeviceTypeEnum {

    /**
     * PC端
     */
    PC("PC"),

    /**
     * 移动端
     */
    APP("APP"),

    /**
     * 小程序端
     */
    MINI("MINI");

    private final String value;

    AuthDeviceTypeEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = PC.getValue().equals(value) || APP.getValue().equals(value) || MINI.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的登录设备类型：{}", value);
        }
    }
}
