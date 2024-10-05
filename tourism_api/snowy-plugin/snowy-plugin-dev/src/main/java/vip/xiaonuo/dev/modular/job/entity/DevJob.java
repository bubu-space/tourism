
package vip.xiaonuo.dev.modular.job.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import vip.xiaonuo.common.pojo.CommonEntity;

/**
 * 定时任务实体类
 *
 * @author gtc
 *
 **/
@Getter
@Setter
@TableName("DEV_JOB")
public class DevJob extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 2)
    private String name;

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 3)
    private String code;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 4)
    private String category;

    /** 任务类名 */
    @ApiModelProperty(value = "任务类名", position = 5)
    private String actionClass;

    /** cron表达式 */
    @ApiModelProperty(value = "cron表达式", position = 6)
    private String cronExpression;

    /** 任务状态 */
    @ApiModelProperty(value = "任务状态", position = 7)
    private String jobStatus;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 8)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 9)
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private String extJson;

}
