package com.snowy.biz.modular.history.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.snowy.common.pojo.CommonEntity;
import com.snowy.common.pojo.CommonFile;

import java.time.LocalDate;
import java.util.List;

@Data
@TableName(value = "biz_history", autoResultMap = true)
public class BizHistory extends CommonEntity {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("景区id")
    private String spotId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("相关图片")
    @TableField(value = "images",typeHandler = JacksonTypeHandler.class)
    private List<CommonFile> images;

    @ApiModelProperty("追溯时间点")
    private LocalDate tracingTime;

    @ApiModelProperty("描述")
    private String description;
}
