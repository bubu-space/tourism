
package vip.xiaonuo.auth.modular.monitor.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 会话查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class AuthSessionPageParam {

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    private String userId;
}
