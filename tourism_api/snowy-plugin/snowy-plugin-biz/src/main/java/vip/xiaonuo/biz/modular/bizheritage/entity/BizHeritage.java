
package vip.xiaonuo.biz.modular.bizheritage.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vip.xiaonuo.common.pojo.CommonFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 历史遗产动态表实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName(value = "biz_heritage",autoResultMap = true)
public class BizHeritage {

    /** 主键 */
    @TableId
    @ApiModelProperty(value = "主键", position = 1)
    private String id;


    /** 遗产名称 */
    @ApiModelProperty(value = "遗产名称", position = 3)
    private String title;

    /** 遗产类型 */
    @ApiModelProperty(value = "遗产类型", position = 4)
    private String category;

    /** 遗产地点 */
    @ApiModelProperty(value = "遗产地点", position = 5)
    private String address;


    /** 遗产代表性图片 */
    @ApiModelProperty(value = "遗产代表性图片", position = 7)
    @TableField(value = "image", typeHandler = JacksonTypeHandler.class)
    private CommonFile image;

    /** 遗产描述 */
    @ApiModelProperty(value = "遗产描述", position = 8)
    private String description;




    /** 删除标志 */
    @ApiModelProperty(value = "删除标志", position = 14)
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 15)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 16)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 17)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 18)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
