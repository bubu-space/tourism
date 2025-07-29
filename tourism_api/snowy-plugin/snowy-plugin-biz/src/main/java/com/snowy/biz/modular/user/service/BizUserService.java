
package com.snowy.biz.modular.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.user.entity.BizUser;
import com.snowy.biz.modular.user.param.BizUserAddParam;
import com.snowy.biz.modular.user.param.BizUserEditParam;
import com.snowy.biz.modular.user.param.BizUserIdParam;
import com.snowy.biz.modular.user.param.BizUserPageParam;

import java.util.List;

/**
 * 用户表Service接口
 *
 * @author snowy
 * 
 **/
public interface BizUserService extends IService<BizUser> {

    /**
     * 获取用户表分页
     *
     * @author snowy
     * 
     */
    Page<BizUser> page(BizUserPageParam bizUserPageParam);

    /**
     * 添加用户表
     *
     * @author snowy
     * 
     */
    void add(BizUserAddParam bizUserAddParam);

    /**
     * 编辑用户表
     *
     * @author snowy
     * 
     */
    void edit(BizUserEditParam bizUserEditParam);

    /**
     * 删除用户表
     *
     * @author snowy
     * 
     */
    void delete(List<BizUserIdParam> bizUserIdParamList);

    /**
     * 获取用户表详情
     *
     * @author snowy
     * 
     */
    BizUser detail(BizUserIdParam bizUserIdParam);

    /**
     * 获取用户表详情
     *
     * @author snowy
     * 
     **/
    BizUser queryEntity(String id);
}
