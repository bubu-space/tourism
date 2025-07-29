
package com.snowy.dev.modular.message.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.message.entity.DevMessage;
import com.snowy.dev.modular.message.param.DevMessageIdParam;
import com.snowy.dev.modular.message.param.DevMessageListParam;
import com.snowy.dev.modular.message.param.DevMessagePageParam;
import com.snowy.dev.modular.message.param.DevMessageSendParam;
import com.snowy.dev.modular.message.result.DevMessageResult;

import java.util.List;

/**
 * 站内信Service接口
 *
 * @author snowy
 *
 **/
public interface DevMessageService extends IService<DevMessage> {

    /**
     * 发送站内信
     *
     * @author snowy
     *
     **/
    void send(DevMessageSendParam devMessageSendParam);

    /**
     * 获取站内信分页
     *
     * @author snowy
     *
     */
    Page<DevMessage> page(DevMessagePageParam devMessagePageParam);

    /**
     * 获取站内信分页，返回JSONObject分页
     *
     * @author snowy
     *
     */
    Page<JSONObject> page(List<String> receiverIdList, String category);

    /**
     * 获取站内信列表
     *
     * @author snowy
     *
     */
    List<DevMessage> list(DevMessageListParam devMessageListParam);

    /**
     * 获取未读站内信数量
     *
     * @author snowy
     *
     */
    Long unreadCount(String loginId);

    /**
     * 删除站内信
     *
     * @author snowy
     *
     **/
    void delete(List<DevMessageIdParam> devMessageIdParamList);

    /**
     * 获取站内信详情
     *
     * @author snowy
     *
     */
    DevMessageResult detail(DevMessageIdParam devMessageIdParam);

    /**
     * 获取站内信详情
     *
     * @author snowy
     *
     */
    DevMessage queryEntity(String id);
}
