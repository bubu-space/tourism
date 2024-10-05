
package vip.xiaonuo.core.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xiaonuo.common.pojo.CommonResult;

/**
 * 全局异常处理器
 *
 * @author gtc
 *
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 不同异常返回不同结果
     *
     * @author gtc
     *
     **/
    @ResponseBody
    @ExceptionHandler
    public CommonResult<String> handleException(Exception e) {
        return GlobalExceptionUtil.getCommonResult(e);
    }
}
