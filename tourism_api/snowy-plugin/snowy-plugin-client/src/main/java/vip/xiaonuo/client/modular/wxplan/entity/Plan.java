package vip.xiaonuo.client.modular.wxplan.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.xiaonuo.client.modular.wxplan.typeHandler.JsonTypeHandler;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* 行程规划表
* @TableName plan
*/
@TableName(value = "biz_plan",autoResultMap = true)
@Data
@NotNull(message = "不能为空")
public class Plan implements Serializable {

    /**
    * id
    */
    @NotBlank(message="[id]不能为空")
    @ApiModelProperty("id")
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
    * 用户id
    */

    @ApiModelProperty("用户id")
    private String userId;
    /**
    * 规划信息：json格式存储
    */
    @ApiModelProperty("规划信息：json格式存储")
    @TableField(typeHandler = JsonTypeHandler.class)
    private List<Content> content;
    /**
    * 删除标志
    */
    @ApiModelProperty("删除标志")
    private String deleteFlag;
    /**
    * 创建用户
    */
    @ApiModelProperty("创建用户")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
    * 更新用户
    */
    @ApiModelProperty("更新用户")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
    * 标题
    */
    @ApiModelProperty("标题")

    private String title;
    /**
    * 计划已完成状态\未完成
    */

    @ApiModelProperty("计划1已完成状态,0未完成")
    private String status;




}
