
package vip.xiaonuo.biz.modular.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.user.entity.BizUser;
import vip.xiaonuo.biz.modular.user.param.BizUserAddParam;
import vip.xiaonuo.biz.modular.user.param.BizUserEditParam;
import vip.xiaonuo.biz.modular.user.param.BizUserIdParam;
import vip.xiaonuo.biz.modular.user.param.BizUserPageParam;

import java.util.List;

/**
 * 用户表Service接口
 *
 * @author gtc
 * 
 **/
public interface BizUserService extends IService<BizUser> {

    /**
     * 获取用户表分页
     *
     * @author gtc
     * 
     */
    Page<BizUser> page(BizUserPageParam bizUserPageParam);

    /**
     * 添加用户表
     *
     * @author gtc
     * 
     */
    void add(BizUserAddParam bizUserAddParam);

    /**
     * 编辑用户表
     *
     * @author gtc
     * 
     */
    void edit(BizUserEditParam bizUserEditParam);

    /**
     * 删除用户表
     *
     * @author gtc
     * 
     */
    void delete(List<BizUserIdParam> bizUserIdParamList);

    /**
     * 获取用户表详情
     *
     * @author gtc
     * 
     */
    BizUser detail(BizUserIdParam bizUserIdParam);

    /**
     * 获取用户表详情
     *
     * @author gtc
     * 
     **/
    BizUser queryEntity(String id);
}
