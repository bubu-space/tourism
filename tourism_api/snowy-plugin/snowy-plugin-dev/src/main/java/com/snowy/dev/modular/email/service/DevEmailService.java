
package com.snowy.dev.modular.email.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.email.entity.DevEmail;
import com.snowy.dev.modular.email.param.*;

import java.util.List;

/**
 * 邮件Service接口
 *
 * @author snowy
 *
 **/
public interface DevEmailService extends IService<DevEmail> {

    /**
     * 发送邮件——本地TXT
     *
     * @author snowy
     *
     **/
    void sendLocal(DevEmailSendLocalTxtParam devEmailSendLocalTxtParam);

    /**
     * 发送邮件——本地HTML
     *
     * @author snowy
     *
     **/
    void sendLocal(DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam);

    /**
     * 发送邮件——阿里云TXT
     *
     * @author snowy
     *
     **/
    void sendAliyun(DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam);

    /**
     * 发送邮件——阿里云HTML
     *
     * @author snowy
     *
     **/
    void sendAliyun(DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam);

    /**
     * 发送邮件——阿里云TMP
     *
     * @author snowy
     *
     **/
    void sendAliyun(DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam);

    /**
     * 发送邮件——腾讯云TXT
     *
     * @author snowy
     *
     **/
    void sendTencent(DevEmailSendTencentTxtParam devEmailSendTencentTxtParam);

    /**
     * 发送邮件——腾讯云HTML
     *
     * @author snowy
     *
     **/
    void sendTencent(DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam);

    /**
     * 发送邮件——腾讯云TMP
     *
     * @author snowy
     *
     **/
    void sendTencent(DevEmailSendTencentTmpParam devEmailSendTencentTmpParam);

    /**
     * 获取邮件分页
     *
     * @author snowy
     *
     */
    Page<DevEmail> page(DevEmailPageParam devEmailPageParam);

    /**
     * 删除邮件
     *
     * @author snowy
     *
     **/
    void delete(List<DevEmailIdParam> devEmailIdParamList);

    /**
     * 获取邮件详情
     *
     * @author snowy
     *
     */
    DevEmail detail(DevEmailIdParam devEmailIdParam);

    /**
     * 获取邮件详情
     *
     * @author snowy
     *
     */
    DevEmail queryEntity(String id);
}
