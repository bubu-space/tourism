
package vip.xiaonuo.sys.modular.org.controller;

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
import vip.xiaonuo.sys.modular.org.entity.SysOrg;
import vip.xiaonuo.sys.modular.org.param.*;
import vip.xiaonuo.sys.modular.org.service.SysOrgService;
import vip.xiaonuo.sys.modular.user.entity.SysUser;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 组织控制器
 *
 * @author gtc
 *
 */
@Api(tags = "组织控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class SysOrgController {

    @Resource
    private SysOrgService sysOrgService;

    /**
     * 获取组织分页
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取组织分页")
    @GetMapping("/sys/org/page")
    public CommonResult<Page<SysOrg>> page(SysOrgPageParam sysOrgPageParam) {
        return CommonResult.data(sysOrgService.page(sysOrgPageParam));
    }

    /**
     * 获取组织树
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取组织树")
    @GetMapping("/sys/org/tree")
    public CommonResult<List<Tree<String>>> tree() {
        return CommonResult.data(sysOrgService.tree());
    }

    /**
     * 添加组织
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("添加组织")
    @CommonLog("添加组织")
    @PostMapping("/sys/org/add")
    public CommonResult<String> add(@RequestBody @Valid SysOrgAddParam sysOrgAddParam) {
        sysOrgService.add(sysOrgAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑组织
     *
     * @author gtc
     * 
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("编辑组织")
    @CommonLog("编辑组织")
    @PostMapping("/sys/org/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysOrgEditParam sysOrgEditParam) {
        sysOrgService.edit(sysOrgEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除组织
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("删除组织")
    @CommonLog("删除组织")
    @PostMapping("/sys/org/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<SysOrgIdParam> sysOrgIdParamList) {
        sysOrgService.delete(sysOrgIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取组织详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取组织详情")
    @GetMapping("/sys/org/detail")
    public CommonResult<SysOrg> detail(@Valid SysOrgIdParam sysOrgIdParam) {
        return CommonResult.data(sysOrgService.detail(sysOrgIdParam));
    }

    /* ====组织部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取组织树选择器")
    @GetMapping("/sys/org/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysOrgService.orgTreeSelector());
    }

    /**
     * 获取用户选择器
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 8)
    @ApiOperation("获取用户选择器")
    @GetMapping("/sys/org/userSelector")
    public CommonResult<Page<SysUser>> userSelector(SysOrgSelectorUserParam sysOrgSelectorUserParam) {
        return CommonResult.data(sysOrgService.userSelector(sysOrgSelectorUserParam));
    }
}
