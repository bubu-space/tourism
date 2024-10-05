
package vip.xiaonuo.biz.modular.ticket.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import vip.xiaonuo.common.pojo.CommonEntity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

/**
 * 景点门票表实体
 *
 * @author gtc
 * 
 **/
@Getter
@Setter
@TableName("biz_ticket")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket extends CommonEntity {

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键", position = 1)
    private String id;

    /** 景区id */
    @ApiModelProperty(value = "景区id", position = 2)
    private String spotId;

    /** 价格 */
    @ApiModelProperty(value = "价格", position = 3)
    private BigDecimal price;

    /** 门票名称 */
    @ApiModelProperty(value = "门票名称", position = 4)
    private String name;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间", position = 5)
    private LocalTime startTime;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间", position = 6)
    private LocalTime endTime;

    /** 库存数量 */
    @ApiModelProperty(value = "库存数量", position = 7)
    private Long inventoryCount;

    /** 销售状态：预售/在售/停售 */
    @ApiModelProperty(value = "销售状态：预售/在售/停售", position = 8)
    private String status;

    @ApiModelProperty(value = "门票描述", position = 15)
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;


    @ApiModelProperty(value = "最大库存",position = 11)
    private Long maxInventory;

}
