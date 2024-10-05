
package vip.xiaonuo.sys.modular.resource.provider;

import org.springframework.stereotype.Service;
import vip.xiaonuo.sys.api.SysMenuApi;
import vip.xiaonuo.sys.modular.resource.service.SysMenuService;

import javax.annotation.Resource;

/**
 * 菜单API接口实现类
 *
 * @author gtc
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
