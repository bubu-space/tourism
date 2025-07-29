
package com.snowy.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 职位API
 *
 * @author snowy
 *
 **/
public interface SysPositionApi {

    /**
     * 根据id获取名称
     *
     * @author snowy
     *
     **/
    String getNameById(String positionId);

    /**
     * 获取职位选择器
     *
     * @author snowy
     *
     **/
    Page<JSONObject> positionSelector(String orgId, String searchKey);
}
