
package vip.xiaonuo.sys.modular.index.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 访问日志结果
 *
 * @author gtc
 *
 */
@Getter
@Setter
public class SysIndexVisLogListResult {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 日志分类 */
    @ApiModelProperty(value = "日志分类", position = 2)
    private String category;

    /** 日志名称 */
    @ApiModelProperty(value = "日志名称", position = 3)
    private String name;

    /** 执行状态 */
    @ApiModelProperty(value = "执行状态", position = 4)
    private String exeStatus;

    /** 具体消息 */
    @ApiModelProperty(value = "具体消息", position = 5)
    private String exeMessage;

    /** 操作ip */
    @ApiModelProperty(value = "操作ip", position = 6)
    private String opIp;

    /** 操作地址 */
    @ApiModelProperty(value = "操作地址", position = 7)
    private String opAddress;

    /** 操作浏览器 */
    @ApiModelProperty(value = "操作浏览器", position = 8)
    private String opBrowser;

    /** 操作系统 */
    @ApiModelProperty(value = "操作系统", position = 9)
    private String opOs;

    /** 操作时间 */
    @ApiModelProperty(value = "操作时间", position = 10)
    private Date opTime;

    /** 操作人姓名 */
    @ApiModelProperty(value = "操作人姓名", position = 11)
    private String opUser;

    /** 签名数据 */
    @ApiModelProperty(value = "签名数据", position = 12)
    private String signData;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 13)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 14)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 15)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 16)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
