
package vip.xiaonuo.dev.modular.email.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.email.entity.DevEmail;
import vip.xiaonuo.dev.modular.email.param.*;

import java.util.List;

/**
 * 邮件Service接口
 *
 * @author gtc
 *
 **/
public interface DevEmailService extends IService<DevEmail> {

    /**
     * 发送邮件——本地TXT
     *
     * @author gtc
     *
     **/
    void sendLocal(DevEmailSendLocalTxtParam devEmailSendLocalTxtParam);

    /**
     * 发送邮件——本地HTML
     *
     * @author gtc
     *
     **/
    void sendLocal(DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam);

    /**
     * 发送邮件——阿里云TXT
     *
     * @author gtc
     *
     **/
    void sendAliyun(DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam);

    /**
     * 发送邮件——阿里云HTML
     *
     * @author gtc
     *
     **/
    void sendAliyun(DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam);

    /**
     * 发送邮件——阿里云TMP
     *
     * @author gtc
     *
     **/
    void sendAliyun(DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam);

    /**
     * 发送邮件——腾讯云TXT
     *
     * @author gtc
     *
     **/
    void sendTencent(DevEmailSendTencentTxtParam devEmailSendTencentTxtParam);

    /**
     * 发送邮件——腾讯云HTML
     *
     * @author gtc
     *
     **/
    void sendTencent(DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam);

    /**
     * 发送邮件——腾讯云TMP
     *
     * @author gtc
     *
     **/
    void sendTencent(DevEmailSendTencentTmpParam devEmailSendTencentTmpParam);

    /**
     * 获取邮件分页
     *
     * @author gtc
     *
     */
    Page<DevEmail> page(DevEmailPageParam devEmailPageParam);

    /**
     * 删除邮件
     *
     * @author gtc
     *
     **/
    void delete(List<DevEmailIdParam> devEmailIdParamList);

    /**
     * 获取邮件详情
     *
     * @author gtc
     *
     */
    DevEmail detail(DevEmailIdParam devEmailIdParam);

    /**
     * 获取邮件详情
     *
     * @author gtc
     *
     */
    DevEmail queryEntity(String id);
}
