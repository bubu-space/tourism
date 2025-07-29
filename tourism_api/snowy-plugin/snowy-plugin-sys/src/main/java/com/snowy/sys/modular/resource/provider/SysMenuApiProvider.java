
package com.snowy.sys.modular.resource.provider;

import org.springframework.stereotype.Service;
import com.snowy.sys.api.SysMenuApi;
import com.snowy.sys.modular.resource.service.SysMenuService;

import javax.annotation.Resource;

/**
 * 菜单API接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class SysMenuApiProvider implements SysMenuApi {

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public String addForGenMenu(String parentId, String busName, String module, String title, String path) {
        return sysMenuService.addForGenMenu(parentId, busName, title, module, path);
    }
}
