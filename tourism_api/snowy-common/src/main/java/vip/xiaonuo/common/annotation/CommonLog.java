
package vip.xiaonuo.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 *
 * @author gtc
 *
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonLog {

    /**
     * 日志的名称，例如:"修改菜单"
     */
    String value() default "未命名";
}
