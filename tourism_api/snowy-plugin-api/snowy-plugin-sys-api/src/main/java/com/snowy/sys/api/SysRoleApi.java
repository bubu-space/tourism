
package com.snowy.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 角色API
 *
 * @author snowy
 *
 **/
public interface SysRoleApi {

    /**
     * 判断组织下是否存在角色
     *
     * @author snowy
     *
     */
    boolean orgHasRole(List<String> orgIdList);

    /**
     * 获取角色选择器
     *
     * @author snowy
     *
     **/
    Page<JSONObject> roleSelector(String orgId, String category, String searchKey, List<String> dataScopeList, boolean excludeSuperAdmin);

    /**
     * 代码生成菜单按钮授权
     *
     * @author snowy
     *
     **/
    void grantForGenMenuAndButton(String menuId);
}
