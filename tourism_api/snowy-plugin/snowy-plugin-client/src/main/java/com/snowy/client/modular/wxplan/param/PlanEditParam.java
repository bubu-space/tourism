package com.snowy.client.modular.wxplan.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.snowy.client.modular.wxplan.entity.Content;
import com.snowy.client.modular.wxplan.typeHandler.JsonTypeHandler;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@TableName(autoResultMap = true)
public class PlanEditParam {
    /** 主键 */
    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotBlank(message = "spotId不能为空")
    private String id;
    @ApiModelProperty(value = "标题", position = 2)
    @NotBlank(message = "标题不能为空")
    private String title;
    @ApiModelProperty(value = "内容", position = 3)
    @NotNull(message = "内容不能为空")
    @TableField(typeHandler = JsonTypeHandler.class)
    private List<Content> content;
    @ApiModelProperty(value = "状态", position = 4)
    @NotBlank(message = "状态不能为空")
    private String status;

}
