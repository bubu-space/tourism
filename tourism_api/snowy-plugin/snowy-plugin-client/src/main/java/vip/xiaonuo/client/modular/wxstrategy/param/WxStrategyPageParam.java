package vip.xiaonuo.client.modular.wxstrategy.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class WxStrategyPageParam {
    /** 当前页 */
    @ApiModelProperty(value = "当前页码")
    private Integer current;

    /** 每页条数 */
    @ApiModelProperty(value = "每页条数")
    private Integer size;

    /** 标题 */
    @ApiModelProperty(value = "标题")
    private String title;
}
