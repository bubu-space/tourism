
package vip.xiaonuo.sys.modular.role.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色授权权限参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysRoleGrantPermissionParam {

    /** 角色id */
    @ApiModelProperty(value = "角色id", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 授权权限信息 */
    @Valid
    @ApiModelProperty(value = "授权权限信息", required = true, position = 2)
    @NotNull(message = "grantInfoList不能为空")
    private List<SysRoleGrantPermission> grantInfoList;

    /**
     * 角色授权权限类
     *
     * @author gtc
     *
     */
    @Getter
    @Setter
    public static class SysRoleGrantPermission {

        /** 接口地址 */
        @ApiModelProperty(value = "接口地址", position = 1)
        @NotBlank(message = "apiUrl不能为空")
        private String apiUrl;

        /** 数据范围分类 */
        @ApiModelProperty(value = "数据范围分类", position = 2)
        @NotBlank(message = "scopeCategory不能为空")
        private String scopeCategory;

        /** 自定义范围组织id集合 */
        @ApiModelProperty(value = "自定义范围组织id集合", position = 3)
        @NotNull(message = "scopeDefineOrgIdList不能为空")
        private List<String> scopeDefineOrgIdList;
    }
}
