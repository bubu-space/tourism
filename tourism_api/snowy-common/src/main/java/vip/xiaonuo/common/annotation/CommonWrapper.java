
package vip.xiaonuo.common.annotation;

import vip.xiaonuo.common.pojo.CommonWrapperInterface;

import java.lang.annotation.*;

/**
 * 自定义包装注解，对响应结果包装
 *
 * @author gtc
 * 
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CommonWrapper {

    /**
     * 具体包装类
     */
    Class<? extends CommonWrapperInterface<?>>[] value();
}
