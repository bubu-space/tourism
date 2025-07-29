
package com.snowy.dev.modular.sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.snowy.common.sse.CommonSseParam;
import java.util.function.Consumer;

/**
 * SSE通信Service接口
 *
 * @author snowy
 *
 **/
public interface DevSseEmitterService {

    /**
     * 创建连接
     *
     * @author snowy
     *
     **/
    public SseEmitter createSseConnect(String clientId,Boolean setHeartBeat,Boolean defaultHeartbeat,Consumer<CommonSseParam> consumer);

    /**
     * 关闭连接
     *
     * @author snowy
     *
     **/
    public void closeSseConnect(String clientId);

    /**
     * 推送消息到所有客户端
     *
     * @author snowy
     *
     **/
    public void sendMessageToAllClient(String msg);

    /**
     * 根据clientId发送消息给某一客户端
     *
     * @author snowy
     *
     **/
    public void sendMessageToOneClient(String clientId, String msg);
}
