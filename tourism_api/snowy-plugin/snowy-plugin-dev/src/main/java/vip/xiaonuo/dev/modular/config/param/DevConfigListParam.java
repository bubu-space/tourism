
package vip.xiaonuo.dev.modular.config.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置列表参数
 *
 * @author gtc
 * 
 */
@Getter
@Setter
public class DevConfigListParam {

    /** 配置分类 */
    @ApiModelProperty(value = "配置分类")
    private String category;
}
