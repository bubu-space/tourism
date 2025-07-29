
package com.snowy.dev.modular.config.provider;

import org.springframework.stereotype.Service;
import com.snowy.dev.api.DevConfigApi;
import com.snowy.dev.modular.config.service.DevConfigService;

import javax.annotation.Resource;

/**
 * 配置API接口实现类
 *
 * @author snowy
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
