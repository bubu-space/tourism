package vip.xiaonuo.biz.modular.history.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.xiaonuo.common.pojo.CommonFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class BizHistoryEditParam {

    @NotNull(message = "id不能为空")
    @ApiModelProperty("id")
    private String id;

    @NotNull(message = "景区id不能为空")
    @ApiModelProperty("景区id")
    private String spotId;

    @NotNull(message = "标题不能为空")
    @ApiModelProperty("标题")
    private String title;


    @ApiModelProperty("相关图片")
    private List<CommonFile> images;

    @NotNull(message = "追溯时间不能为空")
    @ApiModelProperty("追溯时间点")
    private LocalDate tracingTime;

    @NotNull(message = "描述不能为空")
    @ApiModelProperty("描述")
    private String description;
}
