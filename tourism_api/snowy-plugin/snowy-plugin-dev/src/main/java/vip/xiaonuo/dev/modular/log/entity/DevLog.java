
package vip.xiaonuo.dev.modular.log.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 日志实体
 *
 * @author gtc
 *
 */
@Getter
@Setter
@TableName("DEV_LOG")
public class DevLog {

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

    /** 类名称 */
    @ApiModelProperty(value = "类名称", position = 10)
    private String className;

    /** 方法名称 */
    @ApiModelProperty(value = "方法名称", position = 11)
    private String methodName;

    /** 请求方式 */
    @ApiModelProperty(value = "请求方式", position = 12)
    private String reqMethod;

    /** 请求地址 */
    @ApiModelProperty(value = "请求地址", position = 13)
    private String reqUrl;

    /** 请求参数 */
    @ApiModelProperty(value = "请求参数", position = 14)
    private String paramJson;

    /** 返回结果 */
    @ApiModelProperty(value = "返回结果", position = 15)
    private String resultJson;

    /** 操作时间 */
    @ApiModelProperty(value = "操作时间", position = 16)
    private Date opTime;

    /** 操作人姓名 */
    @ApiModelProperty(value = "操作人姓名", position = 17)
    private String opUser;

    /** 签名数据 */
    @ApiModelProperty(value = "签名数据", position = 18)
    private String signData;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 19)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 创建人 */
    @ApiModelProperty(value = "创建人", position = 20)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 21)
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 更新人 */
    @ApiModelProperty(value = "更新人", position = 22)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
