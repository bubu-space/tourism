
package com.snowy.dev.modular.sms.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20210111.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
import com.snowy.common.exception.CommonException;
import com.snowy.dev.api.DevConfigApi;

/**
 * 腾讯云短信工具类
 * 参考文档：https://cloud.tencent.com/document/product/382/43194
 *
 * @author snowy
 *
 */
@Slf4j
public class DevSmsTencentUtil {

    private static SmsClient client;

    private static final String SNOWY_SMS_TENCENT_SECRET_ID_KEY = "SNOWY_SMS_TENCENT_SECRET_ID";
    private static final String SNOWY_SMS_TENCENT_SECRET_KEY_KEY = "SNOWY_SMS_TENCENT_SECRET_KEY";
    private static final String SNOWY_SMS_TENCENT_REGION_ID_KEY = "SNOWY_SMS_TENCENT_REGION_ID";
    private static final String SNOWY_SMS_TENCENT_DEFAULT_SDK_APP_ID_KEY = "SNOWY_SMS_TENCENT_DEFAULT_SDK_APP_ID";
    private static final String SNOWY_SMS_TENCENT_DEFAULT_SIGN_NAME_KEY = "SNOWY_SMS_TENCENT_DEFAULT_SIGN_NAME";

    /**
     * 初始化操作的客户端
     *
     * @author snowy
     *
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* secretId */
        String secretId = devConfigApi.getValueByKey(SNOWY_SMS_TENCENT_SECRET_ID_KEY);

        if(ObjectUtil.isEmpty(secretId)) {
            throw new CommonException("腾讯云短信操作客户端未正确配置：secretId为空");
        }

        /* secretKey */
        String secretKey = devConfigApi.getValueByKey(SNOWY_SMS_TENCENT_SECRET_KEY_KEY);

        if(ObjectUtil.isEmpty(secretKey)) {
            throw new CommonException("腾讯云短信操作客户端未正确配置：secretKey为空");
        }

        /* regionId */
        String regionId = devConfigApi.getValueByKey(SNOWY_SMS_TENCENT_REGION_ID_KEY);

        if(ObjectUtil.isEmpty(regionId)) {
            throw new CommonException("腾讯云短信操作客户端未正确配置：regionId为空");
        }

        client = new SmsClient(new Credential(secretId, secretKey), regionId);
    }

    /**
     * 发送短信
     *
     * @param sdkAppId 短信 SdkAppId，在 短信控制台 添加应用后生成的实际 SdkAppId，示例如1400006666。
     *                 可前往 [短信控制台](https://console.cloud.tencent.com/smsv2/app-manage) 查看
     * @param phoneNumbers 手机号码，支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。
     *                     上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
     * @param signName 短信服务控制台配置且审核通过的短信签名
     * @param templateCode 短信服务控制台配置且审核通过的模板编码
     * @param templateParam 短信模板变量对应的顺序。支持传入多个参数，逗号拼接，示例："张三,15038****76,进行中"
     * @return 发送的结果信息集合 com.tencentcloudapi.sms.v20210111.models.SendStatus
     * @author snowy
     *
     **/
    public static String sendSms(String sdkAppId, String phoneNumbers, String signName, String templateCode, String templateParam) {
        try {
            initClient();
            if(ObjectUtil.isEmpty(sdkAppId)) {
                // sdkAppId为空，则获取默认sdkAppId
                DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);
                sdkAppId = devConfigApi.getValueByKey(SNOWY_SMS_TENCENT_DEFAULT_SDK_APP_ID_KEY);
                if(ObjectUtil.isEmpty(sdkAppId)) {
                    throw new CommonException("腾讯云短信操作客户端未正确配置：sdkAppId为空");
                }
            }
            if(ObjectUtil.isEmpty(signName)) {
                // 签名为空，则获取默认签名
                DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);
                signName = devConfigApi.getValueByKey(SNOWY_SMS_TENCENT_DEFAULT_SIGN_NAME_KEY);
                if(ObjectUtil.isEmpty(signName)) {
                    throw new CommonException("腾讯云短信操作客户端未正确配置：signName为空");
                }
            }
            SendSmsRequest sendSmsRequest = new SendSmsRequest();
            sendSmsRequest.setSmsSdkAppId(sdkAppId);
            sendSmsRequest.setPhoneNumberSet(StrUtil.splitToArray(phoneNumbers, StrUtil.COMMA));
            sendSmsRequest.setSignName(signName);
            sendSmsRequest.setTemplateId(templateCode);
            sendSmsRequest.setTemplateParamSet(ObjectUtil.isNotEmpty(templateParam)?StrUtil.splitToArray(templateParam, StrUtil.COMMA):null);
            SendSmsResponse sendSmsResponse = client.SendSms(sendSmsRequest);
            SendStatus sendStatus = sendSmsResponse.getSendStatusSet()[0];
            String code = sendStatus.getCode().toLowerCase();
            if("ok".equals(code)) {
                return JSONUtil.toJsonStr(sendSmsResponse);
            } else {
                throw new CommonException(sendStatus.getMessage());
            }
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
