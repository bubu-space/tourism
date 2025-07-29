
package com.snowy.dev.api;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.snowy.common.sse.CommonSseParam;
import java.util.function.Consumer;

/**
 * SSE API接口
 *
 * @author snowy
 *
 **/
public interface DevSseApi {

    /**
     * 创建SSE连接
     *
     * @param clientId 客户端id,不传则自动生成
     * @param setHeartBeat 是否设置心跳定时任务,默认为false（true:设置 false:不设置）
     * @param defaultHeartbeat 是否使用默认心跳任务
     * @param consumer 自定义心跳任务,需要自定义实现Consumer接口中的accept方法（setHeartBeat必须为true,defaultHeartbeat为false才有意义）
     * @return 初次建立连接会推送客户端id,状态码为0
     * @author snowy
     *
     **/
    public SseEmitter createSseConnect(String clientId, Boolean setHeartBeat, Boolean defaultHeartbeat, Consumer<CommonSseParam> consumer);

    /**
     * 关闭连接
     *
     * @param clientId 客户端id
     * @author snowy
     *
     **/
    public void closeSseConnect(String clientId);

    /**
     * 推送消息到所有客户端
     *
     * @param msg 推送消息
     * @author snowy
     *
     **/
    public void sendMessageToAllClient(String msg);

    /**
     * 根据clientId发送消息给某一客户端
     *
     * @param clientId 客户端id
     * @param msg 推送消息
     * @author snowy
     *
     **/
    public void sendMessageToOneClient(String clientId, String msg);

}
