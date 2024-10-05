
package vip.xiaonuo.dev.modular.sse.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import vip.xiaonuo.common.sse.CommonSseParam;
import vip.xiaonuo.dev.modular.sse.service.DevSseEmitterService;
import javax.annotation.Resource;
import java.util.function.Consumer;

/**
 * SSE通信控制器
 *
 * @author gtc
 *
 **/
@Api(tags = "SSE通信控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 10)
@RestController
@Validated
public class DevSseEmitterController {

    @Resource
    private DevSseEmitterService devSseEmitterService;

    /**
     * 创建sse连接
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 1)
    @ApiOperation("创建sse连接")
    @GetMapping("/dev/sse/createConnect")
    public SseEmitter createConnect(String clientId,
                                    @RequestParam(required = false)Boolean setHeartBeat,
                                    @RequestParam(required = false)Boolean defaultHeartbeat,
                                    @RequestParam(required = false)Consumer<CommonSseParam> consumer){
        return devSseEmitterService.createSseConnect(clientId,setHeartBeat,defaultHeartbeat,consumer);
    }

    /**
     * 关闭sse连接
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 2)
    @ApiOperation("关闭sse连接")
    @GetMapping("/dev/sse/closeSseConnect")
    public void closeSseConnect(String clientId){
        devSseEmitterService.closeSseConnect(clientId);
    }

    /**
     * 推送消息到所有客户端
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 3)
    @ApiOperation("推送消息到所有客户端")
    @PostMapping("/dev/sse/broadcast")
    public void sendMessageToAllClient(@RequestBody(required = false) String msg){
        devSseEmitterService.sendMessageToAllClient(msg);
    }

    /**
     * 根据clientId发送消息给某一客户端
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 4)
    @ApiOperation("根据clientId发送消息给某一客户端")
    @PostMapping("/dev/sse/sendMessage")
    public void sendMessageToOneClient(String clientId,String msg){
        devSseEmitterService.sendMessageToOneClient(clientId,msg);
    }

}
