
package com.snowy.sys.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 组织API
 *
 * @author snowy
 *
 **/
public interface SysOrgApi {

    /**
     * 根据id获取名称
     *
     * @author snowy
     *
     **/
    String getNameById(String orgId);

    /**
     * 根据组织id获取部门主管id
     *
     * @author snowy
     *
     **/
    String getSupervisorIdByOrgId(String orgId);

    /**
     * 获取组织树选择器
     *
     * @author snowy
     *
     **/
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     *
     * @author snowy
     *
     **/
    Page<JSONObject> orgListSelector(String parentId);
}
