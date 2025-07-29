
package com.snowy.dev.modular.email.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.BatchSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import com.snowy.common.exception.CommonException;
import com.snowy.dev.api.DevConfigApi;

/**
 * 阿里云邮件工具类
 * 参考文档：https://help.aliyun.com/document_detail/29459.html
 *
 * @author snowy
 * 
 **/
@Slf4j
public class DevEmailAliyunUtil {

    private static IAcsClient client;

    private static final String SNOWY_EMAIL_ALIYUN_ACCESS_KEY_ID_KEY = "SNOWY_EMAIL_ALIYUN_ACCESS_KEY_ID";
    private static final String SNOWY_EMAIL_ALIYUN_ACCESS_KEY_SECRET_KEY = "SNOWY_EMAIL_ALIYUN_ACCESS_KEY_SECRET";
    private static final String SNOWY_EMAIL_ALIYUN_REGION_ID_KEY = "SNOWY_EMAIL_ALIYUN_REGION_ID";

    /**
     * 初始化操作的客户端
     *
     * @author snowy
     * 
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* accessKeyId */
        String accessKeyId = devConfigApi.getValueByKey(SNOWY_EMAIL_ALIYUN_ACCESS_KEY_ID_KEY);

        if(ObjectUtil.isEmpty(accessKeyId)) {
            throw new CommonException("阿里云邮件操作客户端未正确配置：accessKeyId为空");
        }

        /* accessKeySecret */
        String accessKeySecret = devConfigApi.getValueByKey(SNOWY_EMAIL_ALIYUN_ACCESS_KEY_SECRET_KEY);

        if(ObjectUtil.isEmpty(accessKeySecret)) {
            throw new CommonException("阿里云邮件操作客户端未正确配置：accessKeySecret为空");
        }

        /* regionId */
        String regionId = devConfigApi.getValueByKey(SNOWY_EMAIL_ALIYUN_REGION_ID_KEY);

        if(ObjectUtil.isEmpty(regionId)) {
            throw new CommonException("阿里云邮件操作客户端未正确配置：regionId为空");
        }

        client = new DefaultAcsClient(DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret));
    }

    /**
     * 发送纯文本邮件（不使用模板，频率限制100 QPS）
     *
     * @param from 管理控制台中配置的发信地址，必传且必须正确
     * @param user 发信人昵称，长度小于15个字符，可不传
     * @param tos 目标地址，多个 email 地址可以用逗号分隔，最多100个地址，必传且必须正确
     * @param subject 邮件主题，必传
     * @param content 邮件 txt 正文，限制28K，必传
     * @return 发送成功的回执id
     * @author snowy
     * 
     **/
    public static String sendTextEmail(String from, String user, String tos, String subject, String content) {
        try {
            initClient();
            SingleSendMailRequest singleSendMailRequest = createSingleSendRequest(from, user, tos, subject, content, false);
            return client.getAcsResponse(singleSendMailRequest).getEnvId();
        } catch (ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 发送HTML邮件（不使用模板，频率限制100 QPS）
     *
     * @param from 管理控制台中配置的发信地址，必传且必须正确
     * @param user 发信人昵称，长度小于15个字符，可不传
     * @param tos 目标地址，多个 email 地址可以用逗号分隔，最多100个地址，必传且必须正确
     * @param subject 邮件主题，必传
     * @param content 邮件 html 正文，限制28K，必传
     * @return 发送成功的回执id
     * @author snowy
     * 
     **/
    public static String sendHtmlEmail(String from, String user, String tos, String subject, String content) {
        try {
            initClient();
            SingleSendMailRequest singleSendMailRequest = createSingleSendRequest(from, user, tos, subject, content, true);
            return client.getAcsResponse(singleSendMailRequest).getEnvId();
        } catch (ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 使用模板发送邮件，国内频率限制是20/min；海外频率限制是10/min。
     *
     * @param from 管理控制台中配置的发信地址，必传且必须正确
     * @param tagName 控制台创建的邮件标签，可不传
     * @param toName 预先创建且上传了收件人的收件人列表名称，必传且必须正确
     * @param templateName 预先创建且通过审核的模板名称，必传且必须正确
     * @return 发送成功的回执id
     * @author snowy
     * 
     **/
    public static String sendEmailWithTemplate(String from, String tagName, String toName, String templateName) {
        try {
            initClient();
            BatchSendMailRequest batchSendMailRequest = createBatchSendRequest(from, tagName, toName, templateName);
            return client.getAcsResponse(batchSendMailRequest).getEnvId();
        } catch (ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }


    /**
     * 创建发送一个邮件的请求
     *
     * @author snowy
     * 
     **/
    private static SingleSendMailRequest createSingleSendRequest(String from, String user, String tos, String subject, String content, boolean isHtml) {
        SingleSendMailRequest request = new SingleSendMailRequest();

        // 控制台创建的发信地址
        request.setAccountName(from);

        // 发信人昵称
        request.setFromAlias(user);

        // 地址类型：0-为随机账号，1-为发信地址
        request.setAddressType(1);

        // 使用管理台配置的回信地址
        request.setReplyToAddress(true);

        // 目标地址
        request.setToAddress(tos);

        // 邮件主题
        request.setSubject(subject);

        //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
        if(isHtml) {
            request.setHtmlBody(content);
        } else  {
            request.setTextBody(content);
        }

        //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
        //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
        request.setSysMethod(MethodType.POST);

        //是否开启追踪功能，开启需要备案，0关闭，1开启
        request.setClickTrace("0");

        return request;
    }

    /**
     * 创建发送批量邮件的请求
     *
     * @author snowy
     * 
     */
    private static BatchSendMailRequest createBatchSendRequest(String from, String tagName, String toName, String templateName) {
        BatchSendMailRequest request = new BatchSendMailRequest();

        // 控制台创建的发信地址
        request.setAccountName(from);

        // 预先创建且上传了收件人的收件人列表名称
        request.setReceiversName(toName);

        // 邮件模板，在控制台创建，相当于邮件的内容
        request.setTemplateName(templateName);

        // 地址类型：0-为随机账号，1-为发信地址
        request.setAddressType(1);

        // 控制台创建的标签
        request.setTagName(tagName);

        //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
        //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
        request.setSysMethod(MethodType.POST);

        //是否开启追踪功能，开启需要备案，0关闭，1开启
        request.setClickTrace("0");

        return request;
    }
}
