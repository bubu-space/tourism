
package com.snowy.dev.modular.sms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.sms.entity.DevSms;
import com.snowy.dev.modular.sms.param.DevSmsIdParam;
import com.snowy.dev.modular.sms.param.DevSmsPageParam;
import com.snowy.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.snowy.dev.modular.sms.param.DevSmsSendTencentParam;

import java.util.List;

/**
 * 短信Service接口
 *
 * @author snowy
 * 
 **/
public interface DevSmsService extends IService<DevSms> {

    /**
     * 发送短信——阿里云
     *
     * @author snowy
     * 
     **/
    void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam);

    /**
     * 发送短信——腾讯云
     *
     * @author snowy
     * 
     **/
    void sendTencent(DevSmsSendTencentParam devSmsSendTencentParam);

    /**
     * 获取短信分页
     *
     * @author snowy
     * 
     */
    Page<DevSms> page(DevSmsPageParam devSmsPageParam);

    /**
     * 删除短信
     *
     * @author snowy
     * 
     **/
    void delete(List<DevSmsIdParam> devSmsIdParamList);

    /**
     * 获取短信详情
     *
     * @author snowy
     * 
     */
    DevSms detail(DevSmsIdParam devSmsIdParam);

    /**
     * 获取短信详情
     *
     * @author snowy
     * 
     */
    DevSms queryEntity(String id);
}
