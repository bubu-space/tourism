package vip.xiaonuo.biz.modular.spot.result;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.xiaonuo.biz.modular.spot.converter.SpotImageTypeHandler;
import vip.xiaonuo.common.pojo.CommonFile;

@Data
public class Namelist {
    @ApiModelProperty(value = "景点名称")
    private String spotName;
    @ApiModelProperty(value = "景点id", position = 1)
    private String id;
    @ApiModelProperty(value = "景点封面", position = 2)
    @TableField(typeHandler = SpotImageTypeHandler.class)
    private CommonFile cover;
    @ApiModelProperty(value = "景点评分", position = 3)
    private Double rate;
    @ApiModelProperty(value = "景点星级", position = 4)
    private Integer startLevel;
}
