
package com.snowy.biz.modular.bizrecommend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonEntity;
import com.snowy.common.pojo.CommonFile;

/**
 * 住/礼/食推荐表实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName(value = "biz_recommend", autoResultMap = true)
public class BizRecommend extends CommonEntity {

    /** id */
    @TableId
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 类型 */
    @ApiModelProperty(value = "类型", position = 2)
    private String category;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 3)
    private String title;

    /** 代表性图片 */
    @ApiModelProperty(value = "代表性图片", position = 4)
    @TableField(typeHandler = JacksonTypeHandler.class)
    private CommonFile cover;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 5)
    private String content;

    /** 排序字段 */
    @ApiModelProperty(value = "排序字段", position = 11)
    private String sort;
}


