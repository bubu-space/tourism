
package com.snowy.dev.modular.sms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.snowy.common.pojo.CommonEntity;

/**
 * 短信实体
 *
 * @author snowy
 *
 **/
@Getter
@Setter
@TableName("dev_sms")
public class DevSms extends CommonEntity {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 短信引擎 */
    @ApiModelProperty(value = "短信引擎", position = 2)
    private String engine;

    /** 手机号 */
    @ApiModelProperty(value = "手机号", position = 3)
    private String phoneNumbers;

    /** 短信签名 */
    @ApiModelProperty(value = "短信签名", position = 4)
    private String signName;

    /** 模板编码 */
    @ApiModelProperty(value = "模板编码", position = 5)
    private String templateCode;

    /** 发送参数 */
    @ApiModelProperty(value = "发送参数", position = 6)
    private String templateParam;

    /** 回执信息 */
    @ApiModelProperty(value = "回执信息", position = 7)
    private String receiptInfo;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 8)
    private String extJson;
}
