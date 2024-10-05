
package vip.xiaonuo.sys.modular.relation.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 关系实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName("SYS_RELATION")
public class SysRelation{

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 对象id */
    @ApiModelProperty(value = "对象id", position = 2)
    private String objectId;

    /** 目标id */
    @ApiModelProperty(value = "目标id", position = 3)
    private String targetId;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 4)
    private String category;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 5)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
