
package vip.xiaonuo.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 角色API
 *
 * @author gtc
 *
 **/
public interface SysRoleApi {

    /**
     * 判断组织下是否存在角色
     *
     * @author gtc
     *
     */
    boolean orgHasRole(List<String> orgIdList);

    /**
     * 获取角色选择器
     *
     * @author gtc
     *
     **/
    Page<JSONObject> roleSelector(String orgId, String category, String searchKey, List<String> dataScopeList, boolean excludeSuperAdmin);

    /**
     * 代码生成菜单按钮授权
     *
     * @author gtc
     *
     **/
    void grantForGenMenuAndButton(String menuId);
}
