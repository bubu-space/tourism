
package vip.xiaonuo.dev.modular.email.provider;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import vip.xiaonuo.dev.api.DevEmailApi;
import vip.xiaonuo.dev.modular.email.param.*;
import vip.xiaonuo.dev.modular.email.service.DevEmailService;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 邮件API接口提供者
 *
 * @author gtc
 *
 **/
@Service
public class DevEmailApiProvider implements DevEmailApi {

    @Resource
    private DevEmailService devEmailService;

    @Override
    public void sendTextEmailLocal(String tos, String subject, String content, List<File> files) {
        DevEmailSendLocalTxtParam devEmailSendLocalTxtParam = new DevEmailSendLocalTxtParam();
        devEmailSendLocalTxtParam.setReceiveAccounts(tos);
        devEmailSendLocalTxtParam.setSubject(subject);
        devEmailSendLocalTxtParam.setContent(content);
        devEmailSendLocalTxtParam.setFiles(files);
        devEmailService.sendLocal(devEmailSendLocalTxtParam);
    }

    @Override
    public void sendHtmlEmailLocal(String tos, String subject, String content, Map<String, InputStream> imageMap, List<File> files) {
        DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam = new DevEmailSendLocalHtmlParam();
        devEmailSendLocalHtmlParam.setReceiveAccounts(tos);
        devEmailSendLocalHtmlParam.setSubject(subject);
        devEmailSendLocalHtmlParam.setContent(content);
        devEmailSendLocalHtmlParam.setImageMap(imageMap);
        devEmailSendLocalHtmlParam.setFiles(files);
        devEmailService.sendLocal(devEmailSendLocalHtmlParam);
    }

    @Override
    public void sendTextEmailAliyun(String from, String user, String tos, String subject, String content) {
        DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam = new DevEmailSendAliyunTxtParam();
        devEmailSendAliyunTxtParam.setSendAccount(from);
        devEmailSendAliyunTxtParam.setSendUser(user);
        devEmailSendAliyunTxtParam.setReceiveAccounts(tos);
        devEmailSendAliyunTxtParam.setSubject(subject);
        devEmailSendAliyunTxtParam.setContent(content);
        devEmailService.sendAliyun(devEmailSendAliyunTxtParam);
    }

    @Override
    public void sendHtmlEmailAliyun(String from, String user, String tos, String subject, String content) {
        DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam = new DevEmailSendAliyunHtmlParam();
        devEmailSendAliyunHtmlParam.setSendAccount(from);
        devEmailSendAliyunHtmlParam.setSendUser(user);
        devEmailSendAliyunHtmlParam.setReceiveAccounts(tos);
        devEmailSendAliyunHtmlParam.setSubject(subject);
        devEmailSendAliyunHtmlParam.setContent(content);
        devEmailService.sendAliyun(devEmailSendAliyunHtmlParam);
    }

    @Override
    public void sendEmailWithTemplateAliyun(String from, String tagName, String toName, String templateName) {
        DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam = new DevEmailSendAliyunTmpParam();
        devEmailSendAliyunTmpParam.setReceiveAccounts(toName);
        devEmailSendAliyunTmpParam.setTagName(tagName);
        devEmailSendAliyunTmpParam.setTemplateName(templateName);
        devEmailService.sendAliyun(devEmailSendAliyunTmpParam);
    }

    @Override
    public void sendTextEmailTencent(String from, String user, String tos, String subject, String content, List<JSONObject> attachmentList) {
        DevEmailSendTencentTxtParam devEmailSendTencentTxtParam = new DevEmailSendTencentTxtParam();
        devEmailSendTencentTxtParam.setSendAccount(from);
        devEmailSendTencentTxtParam.setSendUser(user);
        devEmailSendTencentTxtParam.setReceiveAccounts(tos);
        devEmailSendTencentTxtParam.setSubject(subject);
        devEmailSendTencentTxtParam.setContent(content);
        devEmailSendTencentTxtParam.setAttachmentList(attachmentList);
        devEmailService.sendTencent(devEmailSendTencentTxtParam);
    }

    @Override
    public void sendHtmlEmailTencent(String from, String user, String tos, String subject, String content, List<JSONObject> attachmentList) {
        DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam = new DevEmailSendTencentHtmlParam();
        devEmailSendTencentHtmlParam.setSendAccount(from);
        devEmailSendTencentHtmlParam.setSendUser(user);
        devEmailSendTencentHtmlParam.setReceiveAccounts(tos);
        devEmailSendTencentHtmlParam.setSubject(subject);
        devEmailSendTencentHtmlParam.setContent(content);
        devEmailSendTencentHtmlParam.setAttachmentList(attachmentList);
        devEmailService.sendTencent(devEmailSendTencentHtmlParam);
    }

    @Override
    public void sendEmailWithTemplateTencent(String from, String user, String toId, String templateId, String templateParam, String subject, List<JSONObject> attachmentList) {
        DevEmailSendTencentTmpParam devEmailSendTencentTmpParam = new DevEmailSendTencentTmpParam();
        devEmailSendTencentTmpParam.setSendAccount(from);
        devEmailSendTencentTmpParam.setSendUser(user);
        devEmailSendTencentTmpParam.setReceiveAccounts(toId);
        devEmailSendTencentTmpParam.setTemplateName(templateId);
        devEmailSendTencentTmpParam.setAttachmentList(attachmentList);
        devEmailService.sendTencent(devEmailSendTencentTmpParam);
    }
}
