package vip.xiaonuo.client.modular.wxspot.controller;

import cn.hutool.core.collection.CollStreamUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.biz.modular.comment.param.BizCommentPageParam;
import vip.xiaonuo.biz.modular.comment.result.PageComment;
import vip.xiaonuo.biz.modular.comment.service.BizCommentService;
import vip.xiaonuo.biz.modular.history.entity.BizHistory;
import vip.xiaonuo.biz.modular.history.service.BizHistoryService;
import vip.xiaonuo.biz.modular.spot.entity.Spot;
import vip.xiaonuo.biz.modular.spot.param.SpotPageParam;
import vip.xiaonuo.biz.modular.spot.service.SpotService;
import vip.xiaonuo.biz.modular.ticket.entity.Ticket;
import vip.xiaonuo.biz.modular.ticket.service.TicketService;
import vip.xiaonuo.client.modular.wxspot.param.UserLocation;
import vip.xiaonuo.common.pojo.CommonResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static vip.xiaonuo.biz.modular.spot.service.impl.SpotServiceImpl.GEO_KEY;


@Api(tags = "微信用户景点")
@ApiSupport(author = "SNOWY_TEAM", order = 3)
@RequestMapping("/client/c/spot")
@RestController
@Validated
public class WxSpotController {

    @Autowired
    private SpotService spotService;
    @Autowired
    private BizHistoryService bizHistoryService;
    @Autowired
    private TicketService ticketService;

    @Autowired
    private BizCommentService bizCommentService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @GetMapping("page")
//    @ApiOperationSupport(order = 1)
//    @ApiOperation("获取景点")
//    public CommonResult<Page<Spot>> page(SpotPageParam bizSpotPageParam) {
//        return CommonResult.data(spotService.page(bizSpotPageParam));
//    }


    @GetMapping("getgeospot")
    @ApiOperationSupport(order = 6)
    @ApiOperation("根据地理位置")
    public CommonResult<List<Spot>> getGeoSpot(UserLocation location) {
        Point userpoint = new Point(location.getLongitude(), location.getLatitude());
        Distance distance = new Distance(location.getDistance(), Metrics.KILOMETERS);
        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = stringRedisTemplate.opsForGeo().radius(GEO_KEY,
                new Circle(userpoint, distance),
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().sortAscending());

        List<Spot> collect = Optional.ofNullable(radius)
                .map(GeoResults::getContent)
                .orElse(Collections.emptyList()) // 为空则返回空集合
                .stream()
                .map(geoLocationGeoResult -> geoLocationGeoResult.getContent().getName())
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.isEmpty() ? Collections.emptyList() : spotService.listByIds(list)
                ));

        return CommonResult.data(collect);
    }

    @GetMapping("getspotinfo")
    @ApiOperationSupport(order = 2)
    @ApiOperation("获取景点信息")
    public CommonResult<Spot> getSpotInfo(String spotId) {
        return CommonResult.data(spotService.getById(spotId));
    }

    @GetMapping("getspotheritage")
    @ApiOperationSupport(order = 3)
    @ApiOperation("获取历史动态")
    public CommonResult<List<BizHistory>> getSpotHeritage(String spotId) {
        LambdaQueryWrapper<BizHistory> bizHistoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        bizHistoryLambdaQueryWrapper.eq(BizHistory::getSpotId, spotId);
        bizHistoryLambdaQueryWrapper.orderByDesc(BizHistory::getTracingTime);
        return CommonResult.data(bizHistoryService.list(bizHistoryLambdaQueryWrapper));

    }

    @GetMapping("getticket")
    @ApiOperationSupport(order = 4)
    @ApiOperation("获取门票")
    public CommonResult<List<Ticket>> getTicket(String spotId) {
        List<Ticket> list = ticketService.lambdaQuery().eq(Ticket::getSpotId, spotId).list();
        return CommonResult.data(list);
    }



    @GetMapping("getcomment")
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取评论")
    public CommonResult<Page<PageComment>> getComment(BizCommentPageParam bizCommentPageParam) {
        Page<PageComment> page = bizCommentService.page(bizCommentPageParam);
        return CommonResult.data(page);
    }



}
