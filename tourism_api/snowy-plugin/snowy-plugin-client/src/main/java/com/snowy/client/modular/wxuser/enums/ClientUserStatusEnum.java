package com.snowy.client.modular.wxuser.enums;

import lombok.Getter;
import com.snowy.common.exception.CommonException;


@Getter
public enum ClientUserStatusEnum {


    ENABLE("ENABLE"),


    DISABLED("DISABLED");

    private final String value;

    ClientUserStatusEnum(String value) {
        this.value = value;
    }

    public static void validate(String value) {
        boolean flag = ENABLE.getValue().equals(value) || DISABLED.getValue().equals(value);
        if(!flag) {
            throw new CommonException("不支持的用户状态：{}", value);
        }
    }
}
