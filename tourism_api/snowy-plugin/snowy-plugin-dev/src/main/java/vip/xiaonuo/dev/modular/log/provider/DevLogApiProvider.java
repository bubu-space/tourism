
package vip.xiaonuo.dev.modular.log.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import vip.xiaonuo.auth.core.util.StpLoginUserUtil;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.dev.api.DevLogApi;
import vip.xiaonuo.dev.modular.log.entity.DevLog;
import vip.xiaonuo.dev.modular.log.enums.DevLogCategoryEnum;
import vip.xiaonuo.dev.modular.log.service.DevLogService;
import vip.xiaonuo.dev.modular.log.util.DevLogUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志API接口实现类
 *
 * @author gtc
 *
 */
@Service
public class DevLogApiProvider implements DevLogApi {

    @Resource
    private DevLogService devLogService;

    @Override
    public void executeLoginLog(String userName) {
        DevLogUtil.executeLoginLog(userName);
    }

    @Override
    public void executeLogoutLog(String userName) {
        DevLogUtil.executeLogoutLog(userName);
    }

    @Override
    public List<JSONObject> currentUserVisLogList() {
        return devLogService.page(CommonPageRequest.defaultPage(), new LambdaQueryWrapper<DevLog>()
                .select(DevLog::getName, DevLog::getOpUser, DevLog::getOpTime, DevLog::getOpAddress, DevLog::getOpIp)
                .eq(DevLog::getOpUser, StpLoginUserUtil.getLoginUser().getName())
                .in(DevLog::getCategory, DevLogCategoryEnum.LOGIN.getValue(), DevLogCategoryEnum.LOGOUT.getValue())
                .orderByDesc(DevLog::getCreateTime))
                .getRecords().stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    @Override
    public List<JSONObject> currentUserOpLogList() {
        return devLogService.page(CommonPageRequest.defaultPage(), new LambdaQueryWrapper<DevLog>()
                .select(DevLog::getName, DevLog::getOpUser, DevLog::getOpTime, DevLog::getOpAddress, DevLog::getOpIp)
                .eq(DevLog::getOpUser, StpLoginUserUtil.getLoginUser().getName())
                .in(DevLog::getCategory, DevLogCategoryEnum.OPERATE.getValue(), DevLogCategoryEnum.EXCEPTION.getValue())
                .orderByDesc(DevLog::getCreateTime))
                .getRecords().stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }
}
