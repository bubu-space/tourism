
package vip.xiaonuo.common.timer;

/**
 * 定时器执行者，定时器都要实现本接口，并需要把实现类加入到spring容器中
 *
 * @author gtc
 *
 **/
public interface CommonTimerTaskRunner {

    /**
     * 任务执行的具体内容
     *
     * @author gtc
     *
     **/
    void action();
}
