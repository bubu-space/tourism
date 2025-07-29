
package com.snowy.common.annotation;

import com.snowy.common.pojo.CommonWrapperInterface;

import java.lang.annotation.*;

/**
 * 自定义包装注解，对响应结果包装
 *
 * @author snowy
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
