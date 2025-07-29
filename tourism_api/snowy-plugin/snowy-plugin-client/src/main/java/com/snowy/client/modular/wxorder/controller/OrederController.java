package com.snowy.client.modular.wxorder.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.snowy.auth.core.util.StpClientUtil;
import com.snowy.biz.modular.comment.param.BizCommentAddParam;
import com.snowy.biz.modular.comment.service.BizCommentService;
import com.snowy.biz.modular.order.entity.BizOrder;
import com.snowy.biz.modular.order.entity.BizUserTicket;
import com.snowy.biz.modular.order.param.BizTicketOrderAddParam;
import com.snowy.biz.modular.order.param.BizTicketOrderIdParam;
import com.snowy.biz.modular.order.param.BizTicketOrderPageParam;
import com.snowy.biz.modular.order.service.BizTicketOrderService;
import com.snowy.biz.modular.order.service.BizUserTicketService;
import com.snowy.biz.modular.spot.entity.Spot;
import com.snowy.biz.modular.spot.service.SpotService;
import com.snowy.client.modular.wxuser.entity.ClientUser;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.exception.CommonException;
import com.snowy.common.pojo.CommonResult;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api(tags = "用户门票订单")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@RequestMapping("/client/c/order")
public class OrederController {
    @Autowired
    private BizTicketOrderService bizTicketOrderService;

    @Autowired
    private BizCommentService bizCommentService;

    @Autowired
    private SpotService spotService;

    @Autowired
    private BizUserTicketService bizUserTicketService;


    @GetMapping("page")
    @ApiOperation("用户历史订单")
    @ApiOperationSupport(order = 1)
    public CommonResult<Page<BizOrder>> page(BizTicketOrderPageParam bizTicketOrderPageParam) {
        Optional.ofNullable(bizTicketOrderPageParam.getUserId()).orElseGet(() -> {
            ClientUser wxuser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
            bizTicketOrderPageParam.setUserId(wxuser.getId());
            return null;
        });
        return CommonResult.data(bizTicketOrderService.spotpage(bizTicketOrderPageParam));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("下单")
    @CommonLog("添加门票订单表")
    @PostMapping("/add")
    public CommonResult<String> add(@RequestBody @Valid BizTicketOrderAddParam bizTicketOrderAddParam) throws InterruptedException {
        boolean match = bizTicketOrderAddParam
                .getUserTickInfos()
                .stream()
                .allMatch(user -> ObjectUtil.isAllNotEmpty(user.getUserIdCard(), user.getUserName(), user.getUserPhone()));
        if (!match){
            throw new CommonException("用户信息输入不完整");
        }
        ClientUser wxuser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
        bizTicketOrderAddParam.setUserId(wxuser.getId());
        bizTicketOrderAddParam.setStatus("已支付");
        bizTicketOrderAddParam.setPayMode("微信支付");
        bizTicketOrderService.add(bizTicketOrderAddParam);
        return CommonResult.ok();
    }






    //创建一个异步处理评论任务的机制。当有新的评论任务时，会添加到一个阻塞队列中。一个单独的线程会不断从队列中取出任务并处理

    private BlockingQueue<BizCommentAddParam> commentTasks = new ArrayBlockingQueue<>(1024 * 1024);
    private static final ExecutorService COMMENT_UPDATE_EXECUTOR = Executors.newSingleThreadExecutor();

    public void addCommentTask(BizCommentAddParam commentTask) {
        commentTasks.offer(commentTask);
    }

    private void processCommentTask(BizCommentAddParam commentTask) {
        Spot orderByspotName = spotService.getOrderByspotName(commentTask.getOrderId());
        updateSpotScore(orderByspotName.getId(), commentTask.getScore());
    }


    @PostConstruct
    public void init() {
        COMMENT_UPDATE_EXECUTOR.submit(() -> {
            while (true) {
                try {
                    BizCommentAddParam commentTask = commentTasks.take();
                    processCommentTask(commentTask);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }



    @ApiOperationSupport(order = 3)
    @ApiOperation("对订单评论")
    @PostMapping("addcomment")
    public CommonResult<String> addComment(@RequestBody @Valid BizCommentAddParam bizCommentAddParam) {
        ClientUser wxuser = JSONUtil.toBean(StpClientUtil.getSession().get("wxuser").toString(), ClientUser.class);
        bizCommentAddParam.setUserId(wxuser.getId());
        bizCommentService.add(bizCommentAddParam);

//        Spot orderByspotName = spotService.getOrderByspotName(bizCommentAddParam.getOrderId());
//        updateSpotScore(Long.valueOf(orderByspotName.getId()), bizCommentAddParam.getScore());

        this.addCommentTask(bizCommentAddParam);

        bizTicketOrderService.lambdaUpdate().eq(BizOrder::getId, bizCommentAddParam.getOrderId()).set(BizOrder::getStatus,"已评价").update();
        return CommonResult.ok();
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation("获取订单详情")
    @GetMapping("detail")
    public CommonResult<BizOrder> detail(BizTicketOrderIdParam id) {
        return CommonResult.data(bizTicketOrderService.detail(id));
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("游客信息")
    @GetMapping("userinfo")
    public CommonResult<List<BizUserTicket>> userinfo(String orderId) {
        return CommonResult.data(bizUserTicketService.byorder(orderId));
    }



    public double calculateBayesianAverage(int C, double m, int n, double xBar) {
        return (C * m + n * xBar) / (C + n);
    }
    //采用贝叶斯平均算法更新景点评分
    public void updateSpotScore(String spotId, Double userScore) {
        int C =bizCommentService. getPriorRatingCount();//先验评分数量，即在考虑当前评分之前已有的评分数量
        double m = bizCommentService.getAllSpotsAverageScore();//先验评分平均值，即在考虑当前评分之前所有评分的平均值。


        double currentSpotScore = bizCommentService.getSpotScore(spotId);
        int ratingCount = bizCommentService.getRatingCount(spotId);//加1代表当前评分数量

        double newSpotScore = calculateBayesianAverage(C, m, ratingCount + 1, (currentSpotScore * ratingCount + userScore) / (ratingCount + 1));//xBar当前评分平均值，即新加入的评分和旧评分的平均值。
        spotService.updateSpotScoreInDatabase(spotId, newSpotScore);
    }
}
