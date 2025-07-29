
package com.snowy.sys.api;

/**
 * 菜单API
 *
 * @author snowy
 *
 **/
public interface SysMenuApi {

    /**
     * 代码生成菜单插入
     *
     * @author snowy
     *
     **/
    String addForGenMenu(String parentId, String busName, String module, String title, String path);
}
