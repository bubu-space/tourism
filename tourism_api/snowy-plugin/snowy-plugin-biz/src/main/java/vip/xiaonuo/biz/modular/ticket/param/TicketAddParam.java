
package vip.xiaonuo.biz.modular.ticket.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

/**
 * 景点门票表添加参数
 *
 * @author gtc
 *
 **/
@Getter
@Setter
public class TicketAddParam {

    /** 景区id */
    @ApiModelProperty(value = "景区id", position = 2)
    private String  spotId;

    /** 价格 */
    @ApiModelProperty(value = "价格", position = 3)
    private BigDecimal price;

    /** 门票名称 */
    @ApiModelProperty(value = "门票名称", position = 4)
    private String name;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间", position = 6)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间", position = 6)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

//    /** 库存数量 */
//    @ApiModelProperty(value = "库存数量", position = 7)
//    private Long inventoryCount;

    /** 销售状态：预售/在售/停售 */
    @ApiModelProperty(value = "销售状态：预售/在售/停售", position = 8)
    private String status;

    @ApiModelProperty(value = "门票描述", position = 10)
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty(value = "最大库存",position = 11)
    private Long maxInventory;

}
