
package vip.xiaonuo.biz.modular.bizheritage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.bizheritage.entity.BizHeritage;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageAddParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageEditParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageIdParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritagePageParam;

import java.util.List;

/**
 * 历史遗产动态表Service接口
 *
 * @author gtc
 *
 **/
public interface BizHeritageService extends IService<BizHeritage> {

    /**
     * 获取历史遗产动态表分页
     *
     * @author gtc
     *
     */
    Page<BizHeritage> page(BizHeritagePageParam bizHeritagePageParam);

    /**
     * 添加历史遗产动态表
     *
     * @author gtc
     *
     */
    void add(BizHeritageAddParam bizHeritageAddParam);

    /**
     * 编辑历史遗产动态表
     *
     * @author gtc
     *
     */
    void edit(BizHeritageEditParam bizHeritageEditParam);

    /**
     * 删除历史遗产动态表
     *
     * @author gtc
     *
     */
    void delete(List<BizHeritageIdParam> bizHeritageIdParamList);

    /**
     * 获取历史遗产动态表详情
     *
     * @author gtc
     *
     */
    BizHeritage detail(BizHeritageIdParam bizHeritageIdParam);

    /**
     * 获取历史遗产动态表详情
     *
     * @author gtc
     *
     **/
    BizHeritage queryEntity(String id);
}
