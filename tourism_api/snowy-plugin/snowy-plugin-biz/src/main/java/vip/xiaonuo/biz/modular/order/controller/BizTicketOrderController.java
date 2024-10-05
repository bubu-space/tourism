
package vip.xiaonuo.biz.modular.order.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.common.pojo.CommonValidList;
import vip.xiaonuo.biz.modular.order.entity.BizOrder;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderAddParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderEditParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderIdParam;
import vip.xiaonuo.biz.modular.order.param.BizTicketOrderPageParam;
import vip.xiaonuo.biz.modular.order.service.BizTicketOrderService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 门票订单表控制器
 *
 * @author gtc
 *
 */
@Api(tags = "订单管理")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class BizTicketOrderController {

    @Resource
    private BizTicketOrderService bizTicketOrderService;

    /**
     * 获取门票订单表分页
     *page 方法用于获取门票订单表的分页数据。
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("分页")
    @GetMapping("/biz/ticketorder/page")
    public CommonResult<Page<BizOrder>> page(BizTicketOrderPageParam bizTicketOrderPageParam) {

        return CommonResult.data(bizTicketOrderService.page(bizTicketOrderPageParam));
    }

    /**
     * 添加门票订单表
     *add 方法用于添加门票订单表的数据，包括一些逻辑处理，如检查用户信息是否完整，设置订单状态等。
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("新增")
    @CommonLog("添加门票订单表")
    @PostMapping("/biz/ticketorder/add")
    public CommonResult<String> add(@RequestBody @Valid BizTicketOrderAddParam bizTicketOrderAddParam) throws InterruptedException {
        boolean match = bizTicketOrderAddParam
                .getUserTickInfos()
                .stream()
                .allMatch(user -> ObjectUtil.isAllNotEmpty(user.getUserIdCard(), user.getUserName(), user.getUserPhone()));
        if (!match){
            throw new CommonException("用户信息输入不完整");
        }
        SaBaseLoginUser o = (SaBaseLoginUser) StpUtil.getTokenSession().get("loginUser");
        bizTicketOrderAddParam.setUserId(o.getId());
        bizTicketOrderAddParam.setStatus("已支付");
        bizTicketOrderService.add(bizTicketOrderAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑门票订单表
     *
     * @author gtc
     *
     */
//    @ApiOperationSupport(order = 3)
//    @ApiOperation("编辑")
//    @CommonLog("编辑门票订单表")
//    @PostMapping("/biz/ticketorder/edit")
//    public CommonResult<String> edit(@RequestBody @Valid BizTicketOrderEditParam bizTicketOrderEditParam) {
//        bizTicketOrderService.edit(bizTicketOrderEditParam);
//        return CommonResult.ok();
//    }

    /**
     * 删除门票订单表
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除")
    @CommonLog("删除门票订单表")
    @PostMapping("/biz/ticketorder/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                   CommonValidList<BizTicketOrderIdParam> bizTicketOrderIdParamList) {
        bizTicketOrderService.delete(bizTicketOrderIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取门票订单表详情
     *
     * @author gtc
     *
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("详情")
    @GetMapping("/biz/ticketorder/detail")
    public CommonResult<BizOrder> detail(@Valid BizTicketOrderIdParam bizTicketOrderIdParam) {
        // 返回查询到的门票订单详情
        return CommonResult.data(bizTicketOrderService.detail(bizTicketOrderIdParam));
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation("退款")
    @GetMapping("/biz/ticketorder/refund")
    public CommonResult<String> refund(@Valid BizTicketOrderIdParam bizTicketOrderIdParam) {  // 调用业务逻辑层的退款方法
        bizTicketOrderService.refund(bizTicketOrderIdParam); // 返回操作成功的通用返回对象
        return CommonResult.ok();
    }
}
