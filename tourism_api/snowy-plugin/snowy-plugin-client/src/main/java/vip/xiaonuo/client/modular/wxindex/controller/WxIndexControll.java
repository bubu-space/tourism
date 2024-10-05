package vip.xiaonuo.client.modular.wxindex.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.auth.core.util.StpClientUtil;
import vip.xiaonuo.biz.modular.activitie.entity.BizActivitie;
import vip.xiaonuo.biz.modular.activitie.param.BizActivitieIdParam;
import vip.xiaonuo.biz.modular.activitie.service.BizActivitieService;
import vip.xiaonuo.biz.modular.bizheritage.entity.BizHeritage;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritageIdParam;
import vip.xiaonuo.biz.modular.bizheritage.param.BizHeritagePageParam;
import vip.xiaonuo.biz.modular.bizheritage.service.BizHeritageService;
import vip.xiaonuo.biz.modular.bizrecommend.entity.BizRecommend;
import vip.xiaonuo.biz.modular.bizrecommend.service.BizRecommendService;
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.biz.modular.spot.service.SpotService;
import vip.xiaonuo.biz.modular.strategy.entity.Strategy;
import vip.xiaonuo.biz.modular.strategy.service.StrategyService;
import vip.xiaonuo.biz.modular.ticket.service.TicketService;
import vip.xiaonuo.client.modular.wxindex.result.RedStrategy;
import vip.xiaonuo.client.modular.wxindex.result.WxIndexSpotActivite;
import vip.xiaonuo.client.modular.wxindex.service.IndexService;
import vip.xiaonuo.client.modular.wxplan.entity.Plan;
import vip.xiaonuo.client.modular.wxplan.result.newplan;
import vip.xiaonuo.client.modular.wxplan.service.WxplanService;
import vip.xiaonuo.client.modular.wxuser.entity.ClientUser;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.dev.modular.config.provider.DevConfigApiProvider;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Api(tags = "微信用户首页")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@RequestMapping("/client/c/index")
public class WxIndexControll {

    @Autowired
    private SpotService spotService;

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private WxplanService wxplanService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private BizRecommendService bizRecommendService;

    @Autowired
    private BizActivitieService bizActivitieService;

    @Autowired
    private BizHeritageService bizHeritageService;

    @Autowired
    private DevConfigApiProvider devConfigApiProvider;

    @Autowired
    private TicketService ticketService;

    @GetMapping("getredspot")
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取景点热门")
    public CommonResult<List<Spot>> getRedSpot() {
        List<Spot> spots = spotService.getRedSpot();
        return CommonResult.data(spots);
    }

    @GetMapping("getredstrategy")
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取攻略热门")
    public CommonResult<List<Strategy>> getRedStrategy() {
        List<Strategy> redStrategy1 = strategyService.getRedStrategy();
        return CommonResult.data(redStrategy1);
    }

    @GetMapping("getnewplan")
    @ApiOperationSupport(order = 3)
    @ApiOperation("获取用户最新计划")
    public CommonResult<newplan> getNewPlan() {

        Plan newPlan = wxplanService.getNewPlan();
        newplan newplan = BeanUtil.copyProperties(newPlan, newplan.class);
        return CommonResult.data(newplan);
    }

    @GetMapping("getspotactivite")
    @ApiOperationSupport(order = 4)
    @ApiOperation("获取景点活动")
    public CommonResult<List<WxIndexSpotActivite>> getSpotActivite(String title) {
        List<WxIndexSpotActivite> spots = indexService.getSpotActivite(title);
        return CommonResult.data(spots);
    }



    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @GetMapping("getactiviteinfo")
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取活动详情")
    public CommonResult<BizActivitie> getActiviteInfo(@Valid BizActivitieIdParam bizActivitieIdParam) {

        Runnable updateTask = () -> {
            UpdateWrapper<BizActivitie> updateWrapper = new UpdateWrapper<>();
            updateWrapper.setSql("view_count = view_count + 1")
                    .eq("id", bizActivitieIdParam.getId());
            bizActivitieService.update(updateWrapper);
        };

        Future<?> future = executorService.submit(updateTask);

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            return CommonResult.error();
        }
        return CommonResult.data(bizActivitieService.detail(bizActivitieIdParam));
    }




    @GetMapping("getspotbook")
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取景点预约")
    public CommonResult<List<Spot>> spotBook(String spotname) {
        List<Spot> spots = spotService.spotBook(spotname);
        return CommonResult.data(spots);
    }

    @GetMapping("getrecommend")
    @ApiOperationSupport(order = 6)
    @ApiOperation("获取推荐")
    public CommonResult<List<BizRecommend>> getRecommend(String type) {
        List<BizRecommend> list = bizRecommendService.lambdaQuery().eq(BizRecommend::getCategory,type).last("limit 10").list();
        return CommonResult.data(list);
    }

    @GetMapping("getuserplan")
    @ApiOperationSupport(order = 7)
    @ApiOperation("获取用户计划")
    public CommonResult<List<Plan>> getUserPlan() {
        ClientUser clientUser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
        List<Plan> list = wxplanService.lambdaQuery().eq(Plan::getUserId,clientUser.getId()).last("limit 10").list();
        return CommonResult.data(list);
    }


    @GetMapping("getheritage")
    @ApiOperationSupport(order = 8)
    @ApiOperation("获取遗产")
    public CommonResult<Page<BizHeritage>> page(BizHeritagePageParam bizHeritagePageParam) {
        return CommonResult.data(bizHeritageService.page(bizHeritagePageParam));
    }

    @GetMapping("getheritagedetail")
    @ApiOperationSupport(order = 9)
    @ApiOperation("获取遗产详情")
    public CommonResult<BizHeritage> detail(@Valid BizHeritageIdParam bizHeritageIdParam) {
        return CommonResult.data(bizHeritageService.detail(bizHeritageIdParam));
    }


    @GetMapping("getconfig")
    @ApiOperationSupport(order = 10)
    @ApiOperation("获取配置")
    public CommonResult<String> getConfig(String key) {
        return CommonResult.data(devConfigApiProvider.getValueByKey(key));
    }

}
