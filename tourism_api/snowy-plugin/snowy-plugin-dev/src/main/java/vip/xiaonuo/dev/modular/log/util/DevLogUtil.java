
package vip.xiaonuo.dev.modular.log.util;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.aspectj.lang.JoinPoint;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.util.*;
import vip.xiaonuo.dev.modular.log.entity.DevLog;
import vip.xiaonuo.dev.modular.log.enums.DevLogCategoryEnum;
import vip.xiaonuo.dev.modular.log.enums.DevLogExeStatusEnum;
import vip.xiaonuo.dev.modular.log.service.DevLogService;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志工具类
 *
 * @author gtc
 *
 */
public class DevLogUtil {

    private static final DevLogService devLogService = SpringUtil.getBean(DevLogService.class);

    /**
     * 记录操作日志
     *
     * @author gtc
     *
     */
    public static void executeOperationLog(CommonLog commonLog, String userName, JoinPoint joinPoint, String resultJson) {
        HttpServletRequest request = CommonServletUtil.getRequest();
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.OPERATE.getValue());
            devLog.setName(commonLog.value());
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setClassName(joinPoint.getTarget().getClass().getName());
            devLog.setMethodName(joinPoint.getSignature().getName());
            devLog.setReqMethod(request.getMethod());
            devLog.setReqUrl(request.getRequestURI());
            devLog.setParamJson(CommonJoinPointUtil.getArgsJsonString(joinPoint));
            devLog.setResultJson(resultJson);
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录异常日志
     *
     * @author gtc
     *
     */
    public static void executeExceptionLog(CommonLog commonLog, String userName, JoinPoint joinPoint, Exception exception) {
        HttpServletRequest request = CommonServletUtil.getRequest();
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.EXCEPTION.getValue());
            devLog.setName(commonLog.value());
            devLog.setExeStatus(DevLogExeStatusEnum.FAIL.getValue());
            devLog.setExeMessage(ExceptionUtil.stacktraceToString(exception, Integer.MAX_VALUE));
            devLog.setClassName(joinPoint.getTarget().getClass().getName());
            devLog.setMethodName(joinPoint.getSignature().getName());
            devLog.setReqMethod(request.getMethod());
            devLog.setReqUrl(request.getRequestURI());
            devLog.setParamJson(CommonJoinPointUtil.getArgsJsonString(joinPoint));
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录登录日志
     *
     * @author gtc
     *
     */
    public static void executeLoginLog(String userName) {
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.LOGIN.getValue());
            devLog.setName("用户登录");
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录登出日志
     *
     * @author gtc
     *
     */
    public static void executeLogoutLog(String userName) {
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.LOGOUT.getValue());
            devLog.setName("用户登出");
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 构建基础操作日志
     *
     * @author gtc
     *
     */
    private static DevLog genBasOpLog() {
        HttpServletRequest request = CommonServletUtil.getRequest();
        String ip = CommonIpAddressUtil.getIp(request);
        String loginId;
        try {
            loginId = StpUtil.getLoginIdAsString();
            if (ObjectUtil.isEmpty(loginId)) {
                loginId = "-1";
            }
        } catch (Exception e) {
            loginId = "-1";
        }
        DevLog devLog = new DevLog();
        devLog.setOpIp(CommonIpAddressUtil.getIp(request));
        devLog.setOpAddress(CommonIpAddressUtil.getCityInfo(ip));
        devLog.setOpBrowser(CommonUaUtil.getBrowser(request));
        devLog.setOpOs(CommonUaUtil.getOs(request));
        devLog.setCreateUser(loginId);
        return devLog;
    }

    /**
     * 构建日志完整性保护签名数据
     */
    private static void creatLogSignValue (DevLog devLog) {
        String logStr = devLog.toString().replaceAll(" +","");
        devLog.setSignData(CommonCryptogramUtil.doSignature(logStr));
    }
}