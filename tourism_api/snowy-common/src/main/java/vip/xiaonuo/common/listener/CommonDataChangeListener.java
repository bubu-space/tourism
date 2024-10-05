
package vip.xiaonuo.common.listener;

import cn.hutool.json.JSONArray;

import java.util.List;

/**
 * 通用数据变化侦听器，你可以实现该侦听器接口，在数据新增、更新、删除时进行一些AOP操作
 *
 * @author gtc
 *
 **/
public interface CommonDataChangeListener {

    /**
     * 执行添加，ID集合
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataIdList 被添加的数据ID集合
     * @author gtc
     *
     **/
    void doAddWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行添加，数据集合
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param jsonArray 被添加的数据集合
     * @author gtc
     *
     **/
    void doAddWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行更新，ID集合
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataIdList 被更新的数据ID集合
     * @author gtc
     *
     **/
    void doUpdateWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行更新，数据集合
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param jsonArray 被更新的数据集合
     * @author gtc
     *
     **/
    void doUpdateWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行删除，ID集合
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataIdList 被删除的数据ID集合
     * @author gtc
     *
     **/
    void doDeleteWithDataIdList(String dataType, List<String> dataIdList);
}
