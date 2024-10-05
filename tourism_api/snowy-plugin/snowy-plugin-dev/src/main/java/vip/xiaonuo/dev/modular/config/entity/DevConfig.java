
package vip.xiaonuo.dev.modular.config.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vip.xiaonuo.common.pojo.CommonEntity;

/**
 * 配置实体
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName("DEV_CONFIG")
public class DevConfig extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 配置键 */
    @ApiModelProperty(value = "配置键", position = 2)
    private String configKey;

    /** 配置值 */
    @ApiModelProperty(value = "配置值", position = 3)
    private String configValue;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 4)
    private String category;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 5)
    private String remark;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 6)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 7)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;
}
