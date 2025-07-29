
package com.snowy.biz.modular.bizrecommend.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonFile;

/**
 * 住/礼/食推荐表添加参数
 *
 * @author snowy
 *
 **/
@Getter
@Setter
public class BizRecommendAddParam {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 3)
    private String title;

    /** 类型 */
    @ApiModelProperty(value = "类型", position = 2)
    private String category;


    /** 代表性图片 */
    @ApiModelProperty(value = "代表性图片", position = 4)
    private CommonFile cover;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 5)
    private String content;

    /** 排序字段 */
    @ApiModelProperty(value = "排序字段", position = 11)
    private String sort;

}
