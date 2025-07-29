
package com.snowy.sys.modular.relation.provider;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import com.snowy.sys.api.SysRelationApi;
import com.snowy.sys.modular.relation.entity.SysRelation;
import com.snowy.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.snowy.sys.modular.relation.service.SysRelationService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 关系API接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class SysRelationApiProvider implements SysRelationApi {

    @Resource
    private SysRelationService sysRelationService;

    @Override
    public List<String> getUserIdListByRoleIdList(List<String> roleIdList) {
        return sysRelationService.getRelationObjectIdListByTargetIdListAndCategory(roleIdList,
                SysRelationCategoryEnum.SYS_USER_HAS_ROLE.getValue());
    }

    @Override
    public void removeRoleHasMobileMenuRelation(List<String> targetIdList) {
        sysRelationService.remove(new LambdaQueryWrapper<SysRelation>().in(SysRelation::getTargetId, targetIdList)
                .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_MOBILE_MENU.getValue()));
    }

    @Override
    public void removeRoleHasMobileButtonRelation(List<String> targetIdList, List<String> buttonIdList) {
        sysRelationService.list(new LambdaQueryWrapper<SysRelation>().in(SysRelation::getTargetId, targetIdList)
                .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_MOBILE_MENU.getValue())
                .isNotNull(SysRelation::getExtJson)).forEach(mobileRelation -> {
            JSONObject extJsonObject = JSONUtil.parseObj(mobileRelation.getExtJson());
            List<String> buttonInfoList = extJsonObject.getBeanList("buttonInfo", String.class);
            if (ObjectUtil.isNotEmpty(buttonInfoList)) {
                Set<String> intersectionDistinct = CollectionUtil.intersectionDistinct(buttonIdList, buttonInfoList);
                if(ObjectUtil.isNotEmpty(intersectionDistinct)) {
                    Collection<String> disjunction = CollectionUtil.disjunction(buttonInfoList, intersectionDistinct);
                    extJsonObject.set("buttonInfo", disjunction);
                }
            }
            // 清除对应的角色与移动端菜单信息中的【授权的移动端按钮信息】
            sysRelationService.update(new LambdaUpdateWrapper<SysRelation>().eq(SysRelation::getId, mobileRelation.getId())
                    .set(SysRelation::getExtJson, JSONUtil.toJsonStr(extJsonObject)));
        });
    }
}
