package com.snowy.client.modular.wxstrategy.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.snowy.auth.core.util.StpClientUtil;
import com.snowy.biz.modular.strategy.entity.Strategy;

import com.snowy.client.modular.wxstrategy.param.WxStrategyPageParam;
import com.snowy.client.modular.wxstrategy.service.WxStrageyService;
import com.snowy.client.modular.wxuser.entity.ClientUser;
import com.snowy.common.pojo.CommonResult;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequestMapping("/client/c/wxstrategy/")
@Api(tags = "微信用户活攻略")
@ApiSupport(author = "SNOWY_TEAM", order = 6)
@Validated
@RestController
public class WxStrategyController {
    @Autowired
    private WxStrageyService wxStrageyService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("getstrategy")
    @ApiOperationSupport(order = 1)
    @ApiOperation("攻略分页")
    public CommonResult<Page<Strategy>> page(WxStrategyPageParam strategyPageParam) {
        Page<Strategy> page = wxStrageyService.page(strategyPageParam);
        return CommonResult.data(page);
    }

    @GetMapping("getstrategyinfo")
    @ApiOperationSupport(order = 2)
    @ApiOperation("攻略详情")
    public CommonResult<Strategy> getStrategyInfo(String id) {
        Strategy strategy = wxStrageyService.getById(id);
        return CommonResult.data(strategy);
    }

    @GetMapping("like")
    @ApiOperationSupport(order = 3)
    @ApiOperation("点赞")
    public CommonResult<String> like(Long id) {

        ClientUser wxuser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
        String lockKey = "likeLock:" + wxuser.getId() + ":" + id;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 尝试加锁，设置锁的过期时间为5秒
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                // 使用 Optional 优化 null 检查
                Optional<Double> scoreOptional = Optional.ofNullable(
                        stringRedisTemplate.opsForZSet().score("like:" + wxuser.getId(), id.toString())
                );

                if (scoreOptional.isPresent()) {
                    stringRedisTemplate.opsForZSet().remove("like:" + wxuser.getId(), id.toString());
                    wxStrageyService.cancelLike(id);
                    return CommonResult.ok("取消点赞");
                } else {
                    boolean like = wxStrageyService.like(id);
                    if (like) {
                        try {
                            stringRedisTemplate.opsForZSet().add("like:" + wxuser.getId(), id.toString(), System.currentTimeMillis());
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            } else {
                return CommonResult.error("操作频繁，请稍后再试");
            }
        } catch (InterruptedException e) {
            return CommonResult.error("操作频繁，请稍后再试");
        } finally {
            lock.unlock();
        }


//        Double score = stringRedisTemplate.opsForZSet().score("like:" + wxuser.getId(), id.toString());
//        if (score != null) {
//            stringRedisTemplate.opsForZSet().remove("like:" + wxuser.getId(), id.toString());
//            wxStrageyService.cancelLike(id);
//            return CommonResult.ok("取消点赞");
//        }else{
//                boolean like = wxStrageyService.like(id);
//                if (like) {
//                    try {
//                        stringRedisTemplate.opsForZSet().add("like:" + wxuser.getId(), id.toString(),System.currentTimeMillis());
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//                }
//        }
        return CommonResult.ok();
    }

    @GetMapping("isuserlike")
    @ApiOperationSupport(order = 4)
    @ApiOperation("是否点赞")
    public CommonResult<Boolean> isUserLike(String strategyid) {
        Double score = stringRedisTemplate.opsForZSet().score("like:" + StpClientUtil.getLoginIdAsString(), strategyid.toString());
        return CommonResult.data(score != null);
    }
}
