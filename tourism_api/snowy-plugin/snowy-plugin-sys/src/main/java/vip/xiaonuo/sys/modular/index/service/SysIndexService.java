
package vip.xiaonuo.sys.modular.index.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import vip.xiaonuo.common.pojo.CommonValidList;
import vip.xiaonuo.sys.modular.index.param.*;
import vip.xiaonuo.sys.modular.index.result.*;

import java.util.List;

/**
 * 系统首页Service接口
 *
 * @author gtc
 *
 */
public interface SysIndexService {

    /**
     * 添加当前用户日程
     *
     * @author gtc
     *
     */
    void addSchedule(SysIndexScheduleAddParam sysIndexScheduleAddParam);

    /**
     * 删除日程
     *
     * @author gtc
     *
     */
    void deleteSchedule(CommonValidList<SysIndexScheduleIdParam> sysIndexScheduleIdParamList);

    /**
     * 获取当前用户日程列表
     *
     * @author gtc
     *
     */
    List<SysIndexScheduleListResult> scheduleList(SysIndexScheduleListParam sysIndexScheduleListParam);

    /**
     * 获取当前用户站内信列表
     *
     * @author gtc
     *
     */
    List<SysIndexMessageListResult> messageList(SysIndexMessageListParam sysIndexMessageListParam);

    /**
     * 获取站内信详情
     *
     * @author gtc
     *
     */
    SysIndexMessageDetailResult messageDetail(SysIndexMessageIdParam sysIndexMessageIdParam);

    /**
     * 站内信全部标记已读
     *
     * @author gtc
     *
     */
    void allMessageMarkRead();

    /**
     * 获取当前用户访问日志列表
     *
     * @author gtc
     *
     */
    List<SysIndexVisLogListResult> visLogList();

    /**
     * 获取当前用户操作日志列表
     *
     * @author gtc
     *
     */
    List<SysIndexOpLogListResult> opLogList();

    /**
     * 创建连接
     *
     * @author gtc
     *
     **/
    public SseEmitter createSseConnect(String clientId);
}
