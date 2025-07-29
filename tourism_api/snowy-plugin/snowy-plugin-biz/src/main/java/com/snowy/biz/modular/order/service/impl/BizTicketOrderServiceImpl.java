
package com.snowy.biz.modular.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snowy.biz.modular.order.entity.BizUserTicket;
import com.snowy.biz.modular.order.service.BizUserTicketService;
import com.snowy.biz.modular.ticket.entity.Ticket;
import com.snowy.biz.modular.ticket.service.TicketService;
import com.snowy.biz.modular.order.entity.CacheOrederTick;
import com.snowy.common.enums.CommonSortOrderEnum;
import com.snowy.common.exception.CommonException;
import com.snowy.common.page.CommonPageRequest;
import com.snowy.biz.modular.order.entity.BizOrder;
import com.snowy.biz.modular.order.mapper.BizTicketOrderMapper;
import com.snowy.biz.modular.order.param.BizTicketOrderAddParam;
import com.snowy.biz.modular.order.param.BizTicketOrderEditParam;
import com.snowy.biz.modular.order.param.BizTicketOrderIdParam;
import com.snowy.biz.modular.order.param.BizTicketOrderPageParam;
import com.snowy.biz.modular.order.service.BizTicketOrderService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 门票订单表Service接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class BizTicketOrderServiceImpl extends ServiceImpl<BizTicketOrderMapper, BizOrder> implements BizTicketOrderService {



    @Autowired
    private TicketService ticketService;

    @Autowired
    private BizUserTicketService bizUserTicketService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BizTicketOrderMapper bizTicketOrderMapper;

    @Override
    public Page<BizOrder> page(BizTicketOrderPageParam bizTicketOrderPageParam) {
        QueryWrapper<BizOrder> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(bizTicketOrderPageParam.getUserId())) {
            queryWrapper.lambda().eq(BizOrder::getUserId, bizTicketOrderPageParam.getUserId());
        }
        if(ObjectUtil.isNotEmpty(bizTicketOrderPageParam.getStatus())) {
            queryWrapper.lambda().eq(BizOrder::getStatus, bizTicketOrderPageParam.getStatus());
        }
        if(ObjectUtil.isAllNotEmpty(bizTicketOrderPageParam.getSortField(), bizTicketOrderPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizTicketOrderPageParam.getSortOrder());
            queryWrapper.orderBy(true, bizTicketOrderPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizTicketOrderPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizOrder::getId);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }


    private BlockingQueue<BizTicketOrderAddParam> orderTasks = new ArrayBlockingQueue<>(1024*1024);//阻塞队列
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();//创建线程池
    BizTicketOrderServiceImpl proxy;//代理对象

    @PostConstruct
    private void init(){//初始化执行方法
        SECKILL_ORDER_EXECUTOR.submit(new TicketOrderHandle());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizTicketOrderAddParam bizTicketOrderAddParam) throws InterruptedException {

        // 从缓存中获取门票库存
        String cacheKey = "spot:ticketid:" + bizTicketOrderAddParam.getTicketId();
        String cacheValue = stringRedisTemplate.opsForValue().get(cacheKey);


    //判空
        CacheOrederTick cacheOrderTick = Optional.ofNullable(cacheValue)
                .map(CacheOrederTick::parseObj)
                .orElseGet(() -> {//查询数据库，放到缓存中
                    Ticket ticket = ticketService.lambdaQuery().select(Ticket::getInventoryCount, Ticket::getPrice, Ticket::getStartTime, Ticket::getEndTime)
                            .eq(Ticket::getId, bizTicketOrderAddParam.getTicketId())
                            .one();
//                    Ticket ticket = ticketService.getById(bizTicketOrderAddParam.getTicketId());
                    Optional.ofNullable(ticket).orElseThrow(() -> new CommonException("门票不存在"));
                    CacheOrederTick newCacheOrderTick = CacheOrederTick.builder()
                            .inventory(ticket.getInventoryCount())
                            .tickprice(ticket.getPrice())
                            .startTime(ticket.getStartTime())
                            .endTime(ticket.getEndTime())
                            .build();
                    stringRedisTemplate.opsForValue().set(cacheKey, newCacheOrderTick.paresJson());
                    return newCacheOrderTick;
                });

        // 校验库存 从缓存对象cacheOrderTick中读取当前库存数量。从参数bizTicketOrderAddParam中提取用户本次要购买的门票数量。
        if (cacheOrderTick.getInventory() < bizTicketOrderAddParam.getTicketCount()) {
            throw new CommonException("门票库存不足");//若缓存中的库存数量小于用户购买数量，抛出“门票库存不足”的异常。
        }
        if (bizTicketOrderAddParam.getUserTickInfos().size() != bizTicketOrderAddParam.getTicketCount()) {
            throw new CommonException("购买数量不正确");//获取用户选择的购票数是否和下单的数目相同否则抛出异常
        }

        // 校验价格 计算系统预期总价：缓存中的票价 × 购买数量 比较预期总价与用户提交的总价是否相等 否则抛出异常
        if (cacheOrderTick.getTickprice().multiply(BigDecimal.valueOf(bizTicketOrderAddParam.getTicketCount()))
                .compareTo(bizTicketOrderAddParam.getPrice()) != 0) {//.compareTo作用是比较两个 BigDecimal 的值是否相等。
            throw new CommonException("价格不正确");//0代表相等 否则抛出异常
        }


        orderTasks.put(bizTicketOrderAddParam);//放到队列中

        proxy = (BizTicketOrderServiceImpl) AopContext.currentProxy();//获取当前类的代理对象

    }


    @Transactional(rollbackFor = Exception.class)
    public void createOrder(BizTicketOrderAddParam bizTicketOrderAddParam) throws CommonException{

            String cacheKey = "spot:ticketid:" + bizTicketOrderAddParam.getTicketId();
            CacheOrederTick cacheOrederTick;
            cacheOrederTick = CacheOrederTick.parseObj(stringRedisTemplate.opsForValue().get(cacheKey));
            //从缓存中取到票的数据
            BigDecimal totalPrice = cacheOrederTick.getTickprice().multiply(BigDecimal.valueOf(bizTicketOrderAddParam.getTicketCount()));
            BizOrder bizOrder = BeanUtil.toBean(bizTicketOrderAddParam, BizOrder.class);
            bizOrder.setPrice(totalPrice);
            this.save(bizOrder);
            //校验价格，写入数据库
            bizTicketOrderAddParam.getUserTickInfos().forEach(userInfo -> {
                bizUserTicketService.add(userInfo, bizOrder.getId(),bizTicketOrderAddParam.getTicketId());
            });
            //保存购票人的信息
            ticketService.reduceInventory(bizTicketOrderAddParam.getTicketId(), bizTicketOrderAddParam.getTicketCount());
            cacheOrederTick.setInventory(cacheOrederTick.getInventory() - bizTicketOrderAddParam.getTicketCount());
            stringRedisTemplate.opsForValue().set(cacheKey, cacheOrederTick.paresJson());
            //更新库存

    }

    //从阻塞队列中一直取数据
    private class TicketOrderHandle implements Runnable  {

        @Override
        public void run() {
            while (true){
                try {
                    BizTicketOrderAddParam bizTicketOrderAddParam = orderTasks.take();
                    handleTicketOrder(bizTicketOrderAddParam);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (CommonException e){
                    throw new CommonException(e.getMessage());
                }
            }
        }
    }
//加锁，获取分布式锁
    public void handleTicketOrder(BizTicketOrderAddParam bizTicketOrderAddParam) throws CommonException{
        RLock lock = redissonClient.getLock("lock:ticket:" + bizTicketOrderAddParam.getTicketId());
        boolean hasLock = lock.tryLock( );
        if(!hasLock){
            //获取锁失败
            log.error("不允许重复下单!");
            return;
        }
        try{
            proxy.createOrder(bizTicketOrderAddParam);
            orderTasks.remove(bizTicketOrderAddParam);
        }catch (IllegalStateException e){
            throw new CommonException("下单失败");
        }catch (Exception e){
            throw new CommonException("异常");}
        finally {
            lock.unlock();
        }

    }






    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizTicketOrderEditParam bizTicketOrderEditParam) {
        BizOrder bizOrder = this.queryEntity(bizTicketOrderEditParam.getId());
        BeanUtil.copyProperties(bizTicketOrderEditParam, bizOrder);
        this.updateById(bizOrder);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizTicketOrderIdParam> bizTicketOrderIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizTicketOrderIdParamList, BizTicketOrderIdParam::getId));
    }

    @Override
    public BizOrder detail(BizTicketOrderIdParam bizTicketOrderIdParam) {
        return this.queryEntity(bizTicketOrderIdParam.getId());
    }

    @Override
    public BizOrder queryEntity(String id) {
        BizOrder bizOrder = this.getById(id);
        if(ObjectUtil.isEmpty(bizOrder)) {
            throw new CommonException("门票订单表不存在，id值为：{}", id);
        }
        return bizOrder;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refund(BizTicketOrderIdParam bizTicketOrderIdParam) {
        BizOrder bizOrder = this.queryEntity(bizTicketOrderIdParam.getId());
//        if (!"已支付".equals(bizOrder.getStatus())) {
//            throw new CommonException("订单状态不正确");
//        }
        List<BizUserTicket> list = bizUserTicketService.lambdaQuery().eq(BizUserTicket::getOrderId, bizOrder.getId()).select(BizUserTicket::getId).list();
        bizUserTicketService.removeByIds(CollStreamUtil.toList(list, BizUserTicket::getId));
        bizOrder.setStatus("已退款");
        this.updateById(bizOrder);
    }

    @Override
    public Page<BizOrder> spotpage(BizTicketOrderPageParam bizTicketOrderPageParam) {
        Page<BizOrder> bizOrderPage = new Page<>(bizTicketOrderPageParam.getCurrent(), bizTicketOrderPageParam.getSize());
        return bizTicketOrderMapper.spotImagepage(bizOrderPage, bizTicketOrderPageParam);
    }


}
