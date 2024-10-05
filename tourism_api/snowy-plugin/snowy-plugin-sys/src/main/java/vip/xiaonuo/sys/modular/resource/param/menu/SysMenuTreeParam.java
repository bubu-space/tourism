
package vip.xiaonuo.sys.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 菜单树查询参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysMenuTreeParam {

    /** 模块 */
    @ApiModelProperty(value = "模块")
    private String module;

    /** 名称关键词 */
    @ApiModelProperty(value = "名称关键词")
    private String searchKey;
}
