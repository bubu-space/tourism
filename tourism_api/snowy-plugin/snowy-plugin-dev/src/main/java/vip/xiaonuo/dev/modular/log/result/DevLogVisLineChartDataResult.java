
package vip.xiaonuo.dev.modular.log.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 访问日志折线图数据结果
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class DevLogVisLineChartDataResult {

    /** 日期 */
    @ApiModelProperty(value = "日期", position = 1)
    private String date;

    /** 登录数量 */
    @ApiModelProperty(value = "登录数量", position = 2)
    private Long loginCount;

    /** 登出数量 */
    @ApiModelProperty(value = "登出数量", position = 3)
    private Long logoutCount;
}
