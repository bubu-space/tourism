
package vip.xiaonuo.dev.modular.dict.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 字典列表参数
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevDictListParam {

    /** 父id */
    @ApiModelProperty(value = "父id")
    private String parentId;

    /** 字典分类 */
    @ApiModelProperty(value = "字典分类")
    private String category;
}
