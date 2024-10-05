
package vip.xiaonuo.dev.modular.config.provider;

import org.springframework.stereotype.Service;
import vip.xiaonuo.dev.api.DevConfigApi;
import vip.xiaonuo.dev.modular.config.service.DevConfigService;

import javax.annotation.Resource;

/**
 * 配置API接口实现类
 *
 * @author gtc
 * 
 **/
@Service
public class DevConfigApiProvider implements DevConfigApi {

    @Resource
    private DevConfigService devConfigService;

    @Override
    public String getValueByKey(String key) {
        return devConfigService.getValueByKey(key);
    }
}
