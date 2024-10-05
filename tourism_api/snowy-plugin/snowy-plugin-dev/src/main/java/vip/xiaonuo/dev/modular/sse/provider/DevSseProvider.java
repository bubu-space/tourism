
package vip.xiaonuo.dev.modular.sse.provider;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import vip.xiaonuo.common.sse.CommonSseParam;
import vip.xiaonuo.dev.api.DevSseApi;
import vip.xiaonuo.dev.modular.sse.service.DevSseEmitterService;
import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * SSE API接口提供者
 *
 * @author gtc
 *
 **/
@Service
public class DevSseProvider implements DevSseApi {

    @Resource
    private DevSseEmitterService devSseEmitterService;

    /**
     * 创建SSE连接
     *
     * @author gtc
     *
     **/
    @Override
    public SseEmitter createSseConnect(String clientId, Boolean setHeartBeat, Boolean defaultHeartbeat, Consumer<CommonSseParam> consumer) {
        return devSseEmitterService.createSseConnect(clientId,setHeartBeat,defaultHeartbeat,consumer);
    }

    /**
     * 关闭连接
     *
     * @author gtc
     *
     **/
    @Override
    public void closeSseConnect(String clientId) {
        devSseEmitterService.closeSseConnect(clientId);
    }

    /**
     * 推送消息到所有客户端
     *
     * @author gtc
     *
     **/
    @Override
    public void sendMessageToAllClient(String msg) {
        devSseEmitterService.sendMessageToAllClient(msg);
    }

    /**
     * 根据clientId发送消息给某一客户端
     *
     * @author gtc
     *
     **/
    @Override
    public void sendMessageToOneClient(String clientId, String msg) {
        devSseEmitterService.sendMessageToOneClient(clientId,msg);
    }
}
