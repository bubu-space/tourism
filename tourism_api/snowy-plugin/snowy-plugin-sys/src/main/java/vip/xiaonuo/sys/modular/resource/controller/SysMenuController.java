
package vip.xiaonuo.sys.modular.resource.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;
import vip.xiaonuo.sys.modular.resource.entity.SysMenu;
import vip.xiaonuo.sys.modular.resource.entity.SysModule;
import vip.xiaonuo.sys.modular.resource.param.menu.*;
import vip.xiaonuo.sys.modular.resource.service.SysMenuService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author gtc
 *
 **/
@Api(tags = "菜单控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 5)
@RestController
@Validated
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取菜单分页
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取菜单分页")
    @GetMapping("/sys/menu/page")
    public CommonResult<Page<SysMenu>> page(SysMenuPageParam sysMenuPageParam) {
        return CommonResult.data(sysMenuService.page(sysMenuPageParam));
    }

    /**
     * 获取菜单树
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取菜单树")
    @GetMapping("/sys/menu/tree")
    public CommonResult<List<Tree<String>>> tree(SysMenuTreeParam sysMenuTreeParam) {
        return CommonResult.data(sysMenuService.tree(sysMenuTreeParam));
    }

    /**
     * 添加菜单
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("添加菜单")
    @CommonLog("添加菜单")
    @PostMapping("/sys/menu/add")
    public CommonResult<String> add(@RequestBody @Valid SysMenuAddParam sysMenuAddParam) {
        sysMenuService.add(sysMenuAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑菜单
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("编辑菜单")
    @CommonLog("编辑菜单")
    @PostMapping("/sys/menu/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysMenuEditParam sysMenuEditParam) {
        sysMenuService.edit(sysMenuEditParam);
        return CommonResult.ok();
    }

    /**
     * 更改菜单所属模块
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("更改菜单所属模块")
    @CommonLog("更改菜单所属模块")
    @PostMapping("/sys/menu/changeModule")
    public CommonResult<String> changeModule(@RequestBody @Valid SysMenuChangeModuleParam sysMenuChangeModuleParam) {
        sysMenuService.changeModule(sysMenuChangeModuleParam);
        return CommonResult.ok();
    }

    /**
     * 删除菜单
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("删除菜单")
    @CommonLog("删除菜单")
    @PostMapping("/sys/menu/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<SysMenuIdParam> sysMenuIdParamList) {
        sysMenuService.delete(sysMenuIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取菜单详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取菜单详情")
    @GetMapping("/sys/menu/detail")
    public CommonResult<SysMenu> detail(@Valid SysMenuIdParam sysMenuIdParam) {
        return CommonResult.data(sysMenuService.detail(sysMenuIdParam));
    }

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation("获取模块选择器")
    @GetMapping("/sys/menu/moduleSelector")
    public CommonResult<List<SysModule>> moduleSelector(SysMenuSelectorModuleParam sysMenuSelectorModuleParam) {
        return CommonResult.data(sysMenuService.moduleSelector(sysMenuSelectorModuleParam));
    }

    /**
     * 获取菜单树选择器
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 9)
    @ApiOperation("获取菜单树选择器")
    @GetMapping("/sys/menu/menuTreeSelector")
    public CommonResult<List<Tree<String>>> menuTreeSelector(SysMenuSelectorMenuParam sysMenuSelectorMenuParam) {
        return CommonResult.data(sysMenuService.menuTreeSelector(sysMenuSelectorMenuParam));
    }
}
