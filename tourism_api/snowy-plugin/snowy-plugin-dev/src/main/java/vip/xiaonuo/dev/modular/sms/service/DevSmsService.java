
package vip.xiaonuo.dev.modular.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.sms.entity.DevSms;
import vip.xiaonuo.dev.modular.sms.param.DevSmsIdParam;
import vip.xiaonuo.dev.modular.sms.param.DevSmsPageParam;
import vip.xiaonuo.dev.modular.sms.param.DevSmsSendAliyunParam;
import vip.xiaonuo.dev.modular.sms.param.DevSmsSendTencentParam;

import java.util.List;

/**
 * 短信Service接口
 *
 * @author gtc
 * 
 **/
public interface DevSmsService extends IService<DevSms> {

    /**
     * 发送短信——阿里云
     *
     * @author gtc
     * 
     **/
    void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam);

    /**
     * 发送短信——腾讯云
     *
     * @author gtc
     * 
     **/
    void sendTencent(DevSmsSendTencentParam devSmsSendTencentParam);

    /**
     * 获取短信分页
     *
     * @author gtc
     * 
     */
    Page<DevSms> page(DevSmsPageParam devSmsPageParam);

    /**
     * 删除短信
     *
     * @author gtc
     * 
     **/
    void delete(List<DevSmsIdParam> devSmsIdParamList);

    /**
     * 获取短信详情
     *
     * @author gtc
     * 
     */
    DevSms detail(DevSmsIdParam devSmsIdParam);

    /**
     * 获取短信详情
     *
     * @author gtc
     * 
     */
    DevSms queryEntity(String id);
}
