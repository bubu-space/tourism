
package com.snowy.common.pojo;

import cn.hutool.json.JSONObject;

/**
 * 通用包装接口
 *
 */
public interface CommonWrapperInterface<T> {

    /**
     * 执行包装
     *
     */
    JSONObject doWrap(T wrapperObject);
}
