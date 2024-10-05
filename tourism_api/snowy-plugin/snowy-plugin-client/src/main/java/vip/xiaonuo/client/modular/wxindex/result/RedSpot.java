package vip.xiaonuo.client.modular.wxindex.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RedSpot {
    @ApiModelProperty(value = "景点ID", position = 1)
    private String spotId;
    @ApiModelProperty(value = "景点名称", position = 2)
    private String spotName;

}
