
package vip.xiaonuo.dev.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.dev.modular.dict.entity.DevDict;
import vip.xiaonuo.dev.modular.dict.param.*;

import java.util.List;

/**
 * 字典Service接口
 *
 * @author gtc
 * 
 **/
public interface DevDictService extends IService<DevDict> {

    /**
     * 获取字典分页
     *
     * @author gtc
     * 
     */
    Page<DevDict> page(DevDictPageParam devDictPageParam);

    /**
     * 获取字典列表
     *
     * @author gtc
     * 
     */
    List<DevDict> list(DevDictListParam devDictListParam);

    /**
     * 获取字典树
     *
     * @author gtc
     * 
     */
    List<Tree<String>> tree(DevDictTreeParam devDictTreeParam);

    /**
     * 添加字典
     *
     * @author gtc
     * 
     */
    void add(DevDictAddParam devDictAddParam);

    /**
     * 编辑字典
     *
     * @author gtc
     * 
     */
    void edit(DevDictEditParam devDictEditParam);

    /**
     * 删除字典
     *
     * @author gtc
     * 
     */
    void delete(List<DevDictIdParam> devDictIdParamList);

    /**
     * 获取字典详情
     *
     * @author gtc
     * 
     */
    DevDict detail(DevDictIdParam devDictIdParam);

    /**
     * 获取字典详情
     *
     * @author gtc
     * 
     */
    DevDict queryEntity(String id);
}
