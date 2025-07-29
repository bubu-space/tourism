
package com.snowy.dev.modular.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.snowy.common.timer.CommonTimerTaskRunner;

/**
 * 定时器的一个示例
 *
 * @author snowy
 *
 **/
@Slf4j
@Component
public class DevJobTimerTaskRunner implements CommonTimerTaskRunner {

    private int n = 1;

    @Override
    public void action() {
        log.info("我是一个定时任务，正在在被执行第" + n + "次");
        n = n + 1;
    }
}
