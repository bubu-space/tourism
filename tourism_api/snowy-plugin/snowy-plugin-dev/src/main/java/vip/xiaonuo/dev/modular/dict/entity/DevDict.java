
package vip.xiaonuo.dev.modular.dict.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vip.xiaonuo.common.pojo.CommonEntity;

/**
 * 字典实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName("DEV_DICT")
public class DevDict extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 父id */
    @ApiModelProperty(value = "父id", position = 2)
    private String parentId;

    /** 字典文字 */
    @ApiModelProperty(value = "字典文字", position = 3)
    private String dictLabel;

    /** 字典值 */
    @ApiModelProperty(value = "字典值", position = 4)
    private String dictValue;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 5)
    private String category;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 6)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 7)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
