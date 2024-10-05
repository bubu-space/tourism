
package vip.xiaonuo.biz.modular.strategy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.strategy.entity.Strategy;
import vip.xiaonuo.biz.modular.strategy.param.StrategyAddParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyEditParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyIdParam;
import vip.xiaonuo.biz.modular.strategy.param.StrategyPageParam;

import java.util.List;

/**
 * 旅游攻略表：由用户发表Service接口
 *
 * @author gtc
 *
 **/
public interface StrategyService extends IService<Strategy> {

    /**
     * 获取旅游攻略表：由用户发表分页
     *
     * @author gtc
     *
     */
    Page<Strategy> page(StrategyPageParam strategyPageParam);

    /**
     * 添加旅游攻略表：由用户发表
     *
     * @author gtc
     *
     */
    void add(StrategyAddParam strategyAddParam);

    /**
     * 编辑旅游攻略表：由用户发表
     *
     * @author gtc
     *
     */
    void edit(StrategyEditParam strategyEditParam);

    /**
     * 删除旅游攻略表：由用户发表
     *
     * @author gtc
     *
     */
    void delete(List<StrategyIdParam> strategyIdParamList);

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author gtc
     *
     */
    Strategy detail(StrategyIdParam strategyIdParam);

    /**
     * 获取旅游攻略表：由用户发表详情
     *
     * @author gtc
     *
     **/
    Strategy queryEntity(String id);

    List<Strategy> getRedStrategy();


}
