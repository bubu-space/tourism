
package com.snowy.dev.modular.sse.enums;

import lombok.Getter;

/**
 * SSE通信参数枚举
 *
 * @author snowy
 *
 **/
@Getter
public enum DevSseEmitterParameterEnum {

    /**
     * 通信
     */
    EMITTER("EMITTER"),

    /**
     * 心跳
     */
    FUTURE("FUTURE"),

    /**
     * 用户ID
     */
    LOGINID("LOGINID");

    private final String value;

    DevSseEmitterParameterEnum(String value) {
        this.value = value;
    }

}
