
package vip.xiaonuo.sys.api;

/**
 * 菜单API
 *
 * @author gtc
 *
 **/
public interface SysMenuApi {

    /**
     * 代码生成菜单插入
     *
     * @author gtc
     *
     **/
    String addForGenMenu(String parentId, String busName, String module, String title, String path);
}
