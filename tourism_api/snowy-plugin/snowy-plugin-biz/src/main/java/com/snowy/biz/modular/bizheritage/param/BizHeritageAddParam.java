
package com.snowy.biz.modular.bizheritage.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonFile;

/**
 * 历史遗产动态表添加参数
 *
 * @author snowy
 * 
 **/
@Getter
@Setter
public class BizHeritageAddParam {


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


}
