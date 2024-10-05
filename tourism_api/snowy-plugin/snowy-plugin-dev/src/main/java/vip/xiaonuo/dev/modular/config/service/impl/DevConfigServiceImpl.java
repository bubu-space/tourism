
package vip.xiaonuo.dev.modular.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.common.cache.CommonCacheOperator;
import vip.xiaonuo.common.enums.CommonSortOrderEnum;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.dev.modular.config.entity.DevConfig;
import vip.xiaonuo.dev.modular.config.enums.DevConfigCategoryEnum;
import vip.xiaonuo.dev.modular.config.mapper.DevConfigMapper;
import vip.xiaonuo.dev.modular.config.param.*;
import vip.xiaonuo.dev.modular.config.service.DevConfigService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置Service接口实现类
 *
 * @author gtc
 *
 **/
@Service
public class DevConfigServiceImpl extends ServiceImpl<DevConfigMapper, DevConfig> implements DevConfigService {

    private static final String CONFIG_CACHE_KEY = "dev-config:";

    private static final String SNOWY_SYS_DEFAULT_PASSWORD_KEY = "SNOWY_SYS_DEFAULT_PASSWORD";

    @Resource
    private CommonCacheOperator commonCacheOperator;

    @Override
    public String getValueByKey(String key) {
        // 从缓存中取
//        Object cacheValue = commonCacheOperator.get(CONFIG_CACHE_KEY + key);
//        if(ObjectUtil.isNotEmpty(cacheValue)) {
//            return Convert.toStr(cacheValue);
//        }
        DevConfig devConfig = this.getOne(new LambdaQueryWrapper<DevConfig>().eq(DevConfig::getConfigKey, key));
//        if(ObjectUtil.isNotEmpty(devConfig)) {
//            // 更新到缓存
//            commonCacheOperator.put(CONFIG_CACHE_KEY + key, devConfig.getConfigValue());
            return devConfig.getConfigValue();
//        }
//        return null;
    }

    @Override
    public Page<DevConfig> page(DevConfigPageParam devConfigPageParam) {
        QueryWrapper<DevConfig> queryWrapper = new QueryWrapper<>();
        // 查询部分字段
        queryWrapper.lambda().select(DevConfig::getId, DevConfig::getConfigKey, DevConfig::getConfigValue,
                DevConfig::getCategory, DevConfig::getRemark, DevConfig::getSortCode);
        if(ObjectUtil.isNotEmpty(devConfigPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevConfig::getConfigKey, devConfigPageParam.getSearchKey());
        }
        if(ObjectUtil.isAllNotEmpty(devConfigPageParam.getSortField(), devConfigPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devConfigPageParam.getSortOrder());
            queryWrapper.orderBy(true, devConfigPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devConfigPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(DevConfig::getSortCode);
        }
        queryWrapper.lambda().eq(DevConfig::getCategory, DevConfigCategoryEnum.BIZ_DEFINE.getValue());
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<DevConfig> sysBaseList() {
        DevConfigListParam devConfigListParam = new DevConfigListParam();
        devConfigListParam.setCategory(DevConfigCategoryEnum.SYS_BASE.getValue());
        return this.list(devConfigListParam).stream().filter(devConfig -> !devConfig.getConfigKey()
                .equals(SNOWY_SYS_DEFAULT_PASSWORD_KEY)).collect(Collectors.toList());
    }

    @Override
    public List<DevConfig> list(DevConfigListParam devConfigListParam) {
        LambdaQueryWrapper<DevConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询部分字段
        lambdaQueryWrapper.select(DevConfig::getId, DevConfig::getConfigKey, DevConfig::getConfigValue,
                DevConfig::getCategory, DevConfig::getSortCode);
        if(ObjectUtil.isNotEmpty(devConfigListParam.getCategory())) {
            lambdaQueryWrapper.eq(DevConfig::getCategory, devConfigListParam.getCategory());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public void add(DevConfigAddParam devConfigAddParam) {
        checkParam(devConfigAddParam);
        DevConfig devConfig = BeanUtil.toBean(devConfigAddParam, DevConfig.class);
        devConfig.setCategory(DevConfigCategoryEnum.BIZ_DEFINE.getValue());
        this.save(devConfig);
    }

    private void checkParam(DevConfigAddParam devConfigAddParam) {
        boolean hasSameConfig = this.count(new LambdaQueryWrapper<DevConfig>()
                .eq(DevConfig::getConfigKey, devConfigAddParam.getConfigKey())) > 0;
        if (hasSameConfig) {
            throw new CommonException("存在重复的配置，配置键为：{}", devConfigAddParam.getConfigKey());
        }
    }

    @Override
    public void edit(DevConfigEditParam devConfigEditParam) {
        DevConfig devConfig = this.queryEntity(devConfigEditParam.getId());
        checkParam(devConfigEditParam);
        BeanUtil.copyProperties(devConfigEditParam, devConfig);
        devConfig.setCategory(DevConfigCategoryEnum.BIZ_DEFINE.getValue());
        this.updateById(devConfig);
        // 移除对应的缓存
        commonCacheOperator.remove(CONFIG_CACHE_KEY + devConfig.getConfigKey());
    }

    private void checkParam(DevConfigEditParam devConfigEditParam) {
        boolean hasSameConfig = this.count(new LambdaQueryWrapper<DevConfig>()
                .eq(DevConfig::getConfigKey, devConfigEditParam.getConfigKey())
                .ne(DevConfig::getId, devConfigEditParam.getId())) > 0;
        if (hasSameConfig) {
            throw new CommonException("存在重复的配置，配置键为：{}", devConfigEditParam.getConfigKey());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevConfigIdParam> devConfigIdParamList) {
        List<String> devConfigIdList = CollStreamUtil.toList(devConfigIdParamList, DevConfigIdParam::getId);
        if(ObjectUtil.isNotEmpty(devConfigIdList)) {
            List<DevConfig> devConfigList = this.listByIds(devConfigIdList);
            if(ObjectUtil.isNotEmpty(devConfigList)) {
                List<String> devConfigResultList = CollectionUtil.newArrayList(devConfigList.stream()
                        .map(DevConfig::getCategory).collect(Collectors.toSet()));
                if(devConfigResultList.size() != 1 || !devConfigResultList.get(0).equals(DevConfigCategoryEnum.BIZ_DEFINE.getValue())) {
                    throw new CommonException("不可删除系统内置配置");
                }
                List<DevConfig> deleteDevConfigList = this.listByIds(devConfigIdList);
                // 执行删除
                this.removeByIds(devConfigIdList);

                deleteDevConfigList.forEach(devConfig -> {
                    // 移除对应的缓存
                    commonCacheOperator.remove(CONFIG_CACHE_KEY + devConfig.getConfigKey());
                });
            }
        }
    }

    @Override
    public DevConfig detail(DevConfigIdParam devConfigIdParam) {
        return this.queryEntity(devConfigIdParam.getId());
    }

    @Override
    public DevConfig queryEntity(String id) {
        DevConfig devConfig = this.getById(id);
        if(ObjectUtil.isEmpty(devConfig)) {
            throw new CommonException("配置不存在，id值为：{}", id);
        }
        return devConfig;
    }

    @Override
    public void editBatch(List<DevConfigBatchParam> devConfigBatchParamList) {
        devConfigBatchParamList.forEach(devConfigBatchParam -> {
            this.update(new LambdaUpdateWrapper<DevConfig>()
                    .eq(DevConfig::getConfigKey, devConfigBatchParam.getConfigKey())
                    .set(DevConfig::getConfigValue, devConfigBatchParam.getConfigValue()));
            // 移除对应的缓存
            commonCacheOperator.remove(CONFIG_CACHE_KEY + devConfigBatchParam.getConfigKey());
        });
    }
}
