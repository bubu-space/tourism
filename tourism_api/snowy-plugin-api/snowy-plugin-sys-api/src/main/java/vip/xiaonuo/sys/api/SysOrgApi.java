
package vip.xiaonuo.sys.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 组织API
 *
 * @author gtc
 *
 **/
public interface SysOrgApi {

    /**
     * 根据id获取名称
     *
     * @author gtc
     *
     **/
    String getNameById(String orgId);

    /**
     * 根据组织id获取部门主管id
     *
     * @author gtc
     *
     **/
    String getSupervisorIdByOrgId(String orgId);

    /**
     * 获取组织树选择器
     *
     * @author gtc
     *
     **/
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     *
     * @author gtc
     *
     **/
    Page<JSONObject> orgListSelector(String parentId);
}
