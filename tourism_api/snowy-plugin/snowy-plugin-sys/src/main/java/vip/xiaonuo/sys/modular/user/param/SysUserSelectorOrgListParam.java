
package vip.xiaonuo.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 组织列表选择器参数
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
public class SysUserSelectorOrgListParam {

    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 父id */
    @ApiModelProperty(value = "父id")
    private String parentId;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
