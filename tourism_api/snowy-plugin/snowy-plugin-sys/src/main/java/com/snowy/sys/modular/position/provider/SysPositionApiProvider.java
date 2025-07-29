
package com.snowy.sys.modular.position.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.snowy.sys.api.SysPositionApi;
import com.snowy.sys.modular.position.param.SysPositionSelectorPositionParam;
import com.snowy.sys.modular.position.service.SysPositionService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 职位API接口提供者
 *
 * @author snowy
 *
 **/
@Service
public class SysPositionApiProvider implements SysPositionApi {

    @Resource
    private SysPositionService sysPositionService;

    @Override
    public String getNameById(String positionId) {
        return sysPositionService.queryEntity(positionId).getName();
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> positionSelector(String orgId, String searchKey) {
        SysPositionSelectorPositionParam sysPositionSelectorPositionParam = new SysPositionSelectorPositionParam();
        sysPositionSelectorPositionParam.setOrgId(orgId);
        sysPositionSelectorPositionParam.setSearchKey(searchKey);
        return BeanUtil.toBean(sysPositionService.positionSelector(sysPositionSelectorPositionParam), Page.class);
    }
}
