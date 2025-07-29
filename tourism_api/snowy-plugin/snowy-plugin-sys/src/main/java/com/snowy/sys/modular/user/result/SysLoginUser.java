
package com.snowy.sys.modular.user.result;

import com.snowy.auth.core.pojo.SaBaseLoginUser;
import com.snowy.sys.modular.user.enums.SysUserStatusEnum;

/**
 * 登录用户对象
 *
 * @author snowy
 *
 **/
public class SysLoginUser extends SaBaseLoginUser {

    /**
     * 实现是否可以登录
     *
     * @author snowy
     *
     **/
    @Override
    public Boolean getEnabled() {
        // 仅判断状态是否正常，可自行扩展
        return getUserStatus().equals(SysUserStatusEnum.ENABLE.getValue());
    }
}
