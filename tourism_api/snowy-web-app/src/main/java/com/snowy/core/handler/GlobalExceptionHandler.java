
package com.snowy.core.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.snowy.common.pojo.CommonResult;

/**
 * 全局异常处理器
 *
 * @author snowy
 *
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 不同异常返回不同结果
     *
     * @author snowy
     *
     **/
    @ResponseBody
    @ExceptionHandler
    public CommonResult<String> handleException(Exception e) {
        return GlobalExceptionUtil.getCommonResult(e);
    }
}
