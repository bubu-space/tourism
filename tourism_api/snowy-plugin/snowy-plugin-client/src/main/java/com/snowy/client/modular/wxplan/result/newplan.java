package com.snowy.client.modular.wxplan.result;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.snowy.client.modular.wxplan.entity.Content;

import java.util.Date;
import java.util.List;

@Data
public class newplan {
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    @ApiModelProperty(value = "创建时间", position = 2)
    private Date createTime;

    @ApiModelProperty(value = "规划信息：json格式存储", position = 3)
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Content> content;

    @ApiModelProperty(value = "浏览量", position = 4)
    private String title;

    @ApiModelProperty(value = "状态", position = 5)
    private String status;
}
