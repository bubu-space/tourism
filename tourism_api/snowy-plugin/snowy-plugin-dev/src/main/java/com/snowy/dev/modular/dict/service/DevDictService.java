
package com.snowy.dev.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.dev.modular.dict.entity.DevDict;
import com.snowy.dev.modular.dict.param.*;

import java.util.List;

/**
 * 字典Service接口
 *
 * @author snowy
 * 
 **/
public interface DevDictService extends IService<DevDict> {

    /**
     * 获取字典分页
     *
     * @author snowy
     * 
     */
    Page<DevDict> page(DevDictPageParam devDictPageParam);

    /**
     * 获取字典列表
     *
     * @author snowy
     * 
     */
    List<DevDict> list(DevDictListParam devDictListParam);

    /**
     * 获取字典树
     *
     * @author snowy
     * 
     */
    List<Tree<String>> tree(DevDictTreeParam devDictTreeParam);

    /**
     * 添加字典
     *
     * @author snowy
     * 
     */
    void add(DevDictAddParam devDictAddParam);

    /**
     * 编辑字典
     *
     * @author snowy
     * 
     */
    void edit(DevDictEditParam devDictEditParam);

    /**
     * 删除字典
     *
     * @author snowy
     * 
     */
    void delete(List<DevDictIdParam> devDictIdParamList);

    /**
     * 获取字典详情
     *
     * @author snowy
     * 
     */
    DevDict detail(DevDictIdParam devDictIdParam);

    /**
     * 获取字典详情
     *
     * @author snowy
     * 
     */
    DevDict queryEntity(String id);
}
