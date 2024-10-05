
package vip.xiaonuo.sys.modular.user.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户拥有的权限结果
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysUserOwnPermissionResult {

    /** 用户id */
    @ApiModelProperty(value = "角色id", position = 1)
    private String id;

    /** 已授权权限信息 */
    @ApiModelProperty(value = "已授权权限信息", position = 2)
    private List<SysUserOwnPermission> grantInfoList;

    /**
     * 用户拥有资源类
     *
     * @author gtc
     *
     */
    @Getter
    @Setter
    public static class SysUserOwnPermission {

        /** 菜单id */
        @ApiModelProperty(value = "接口地址", position = 1)
        private String apiUrl;

        /** 数据范围分类 */
        @ApiModelProperty(value = "数据范围分类", position = 1)
        private String scopeCategory;

        /** 自定义范围组织id集合 */
        @ApiModelProperty(value = "自定义范围组织id集合", position = 2)
        private List<String> scopeDefineOrgIdList;

    }
}
