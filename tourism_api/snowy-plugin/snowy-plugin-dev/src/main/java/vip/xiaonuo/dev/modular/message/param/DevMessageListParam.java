
package vip.xiaonuo.dev.modular.message.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 站内信列表参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class DevMessageListParam {

    /** 接收人id集合 */
    @ApiModelProperty(value = "接收人id集合")
    private List<String> receiverIdList;

    /** 条数" */
    @ApiModelProperty(value = "条数")
    private Integer limit;
}
