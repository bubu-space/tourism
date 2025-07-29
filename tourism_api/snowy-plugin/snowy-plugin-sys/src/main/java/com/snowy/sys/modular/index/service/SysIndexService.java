
package com.snowy.sys.modular.index.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.snowy.common.pojo.CommonValidList;
import com.snowy.sys.modular.index.param.*;
import com.snowy.sys.modular.index.result.*;

import java.util.List;

/**
 * 系统首页Service接口
 *
 * @author snowy
 *
 */
public interface SysIndexService {

    /**
     * 添加当前用户日程
     *
     * @author snowy
     *
     */
    void addSchedule(SysIndexScheduleAddParam sysIndexScheduleAddParam);

    /**
     * 删除日程
     *
     * @author snowy
     *
     */
    void deleteSchedule(CommonValidList<SysIndexScheduleIdParam> sysIndexScheduleIdParamList);

    /**
     * 获取当前用户日程列表
     *
     * @author snowy
     *
     */
    List<SysIndexScheduleListResult> scheduleList(SysIndexScheduleListParam sysIndexScheduleListParam);

    /**
     * 获取当前用户站内信列表
     *
     * @author snowy
     *
     */
    List<SysIndexMessageListResult> messageList(SysIndexMessageListParam sysIndexMessageListParam);

    /**
     * 获取站内信详情
     *
     * @author snowy
     *
     */
    SysIndexMessageDetailResult messageDetail(SysIndexMessageIdParam sysIndexMessageIdParam);

    /**
     * 站内信全部标记已读
     *
     * @author snowy
     *
     */
    void allMessageMarkRead();

    /**
     * 获取当前用户访问日志列表
     *
     * @author snowy
     *
     */
    List<SysIndexVisLogListResult> visLogList();

    /**
     * 获取当前用户操作日志列表
     *
     * @author snowy
     *
     */
    List<SysIndexOpLogListResult> opLogList();

    /**
     * 创建连接
     *
     * @author snowy
     *
     **/
    public SseEmitter createSseConnect(String clientId);
}
