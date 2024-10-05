
package vip.xiaonuo.sys.modular.user.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户拥有的资源结果
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class SysUserOwnResourceResult {

    /** 用户id */
    @ApiModelProperty(value = "用户id", position = 1)
    private String id;

    /** 已授权资源信息 */
    @ApiModelProperty(value = "已授权资源信息", position = 2)
    private List<SysUserOwnResource> grantInfoList;

    /**
     * 用户拥有资源类
     *
     * @author gtc
     * 
     */
    @Getter
    @Setter
    public static class SysUserOwnResource {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        private String menuId;

        /** 按钮id集合 */
        @ApiModelProperty(value = "按钮id集合", position = 2)
        private List<String> buttonInfo;
    }
}
