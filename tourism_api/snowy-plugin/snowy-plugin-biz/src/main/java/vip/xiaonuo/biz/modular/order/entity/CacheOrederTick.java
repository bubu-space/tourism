package vip.xiaonuo.biz.modular.order.entity;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CacheOrederTick implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    //库存
    private Long inventory;

    private BigDecimal tickprice;

    /** 开始时间 */
    private LocalTime startTime;

    /** 结束时间 */
    private LocalTime endTime;

    public String paresJson(){
        return JSONUtil.toJsonStr(this);
    }

    public static CacheOrederTick parseObj(String json){
        return JSONUtil.toBean(json, CacheOrederTick.class);
    }
}
