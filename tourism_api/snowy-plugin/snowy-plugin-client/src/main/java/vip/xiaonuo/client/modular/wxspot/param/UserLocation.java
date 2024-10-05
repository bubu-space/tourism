package vip.xiaonuo.client.modular.wxspot.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLocation {
    @ApiModelProperty(value = "经度")
    public Double longitude;
    @ApiModelProperty(value = "纬度")
    public Double latitude;
    @ApiModelProperty(value = "距离")
    public Integer distance;
}
