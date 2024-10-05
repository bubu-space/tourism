
package vip.xiaonuo.auth.modular.monitor.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 会话统计结果
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class AuthSessionAnalysisResult {

    /** 当前会话总数量 */
    @ApiModelProperty(value = "当前会话总数量", position = 1)
    private String currentSessionTotalCount;

    /** 最大签发令牌数 */
    @ApiModelProperty(value = "最大签发令牌数", position = 2)
    private String maxTokenCount;

    /** 最近1小时会话数 */
    @ApiModelProperty(value = "最近1小时会话数", position = 3)
    private String oneHourNewlyAdded;

    /** BC端会话比例 */
    @ApiModelProperty(value = "BC端会话比例", position = 4)
    private String proportionOfBAndC;
}
