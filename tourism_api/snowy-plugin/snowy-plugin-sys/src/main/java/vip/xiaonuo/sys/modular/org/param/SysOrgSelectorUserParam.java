
package vip.xiaonuo.sys.modular.org.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户选择器参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class SysOrgSelectorUserParam {

    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 组织id */
    @ApiModelProperty(value = "组织id")
    private String orgId;

    /** 姓名关键词 */
    @ApiModelProperty(value = "姓名关键词")
    private String searchKey;
}
