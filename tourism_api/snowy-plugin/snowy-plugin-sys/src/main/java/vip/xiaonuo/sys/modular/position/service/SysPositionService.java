
package vip.xiaonuo.sys.modular.position.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.sys.modular.position.entity.SysPosition;
import vip.xiaonuo.sys.modular.position.param.*;

import java.util.List;

/**
 * 职位Service接口
 *
 * @author gtc
 *
 **/
public interface SysPositionService extends IService<SysPosition> {

    /**
     * 获取职位分页
     *
     * @author gtc
     *
     */
    Page<SysPosition> page(SysPositionPageParam sysPositionPageParam);

    /**
     * 添加职位
     *
     * @author gtc
     *
     */
    void add(SysPositionAddParam sysPositionAddParam);

    /**
     * 编辑职位
     *
     * @author gtc
     *
     */
    void edit(SysPositionEditParam sysPositionEditParam);

    /**
     * 删除职位
     *
     * @author gtc
     *
     */
    void delete(List<SysPositionIdParam> sysPositionIdParamList);

    /**
     * 获取职位详情
     *
     * @author gtc
     *
     */
    SysPosition detail(SysPositionIdParam sysPositionIdParam);

    /**
     * 获取职位详情
     *
     * @author gtc
     *
     **/
    SysPosition queryEntity(String id);

    /**
     * 根据id获取数据
     *
     * @author gtc
     *
     **/
    SysPosition getById(List<SysPosition> originDataList, String id);

    /**
     * 根据组织id和职位名称获取职位id，有则返回，无则创建
     *
     * @author gtc
     *
     **/
    String getPositionIdByPositionNameWithCreate(String orgId, String positionName);

    /* ====职位部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     *
     * @author gtc
     *
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取职位选择器
     *
     * @author gtc
     *
     */
    Page<SysPosition> positionSelector(SysPositionSelectorPositionParam sysPositionSelectorPositionParam);
}
