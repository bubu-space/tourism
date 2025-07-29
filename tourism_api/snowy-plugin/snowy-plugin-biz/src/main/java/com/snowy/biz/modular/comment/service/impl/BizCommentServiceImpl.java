
package com.snowy.biz.modular.comment.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.snowy.biz.modular.comment.result.PageComment;
import com.snowy.biz.modular.order.service.BizTicketOrderService;
import com.snowy.common.exception.CommonException;
import com.snowy.biz.modular.comment.entity.BizComment;
import com.snowy.biz.modular.comment.mapper.BizCommentMapper;
import com.snowy.biz.modular.comment.param.BizCommentAddParam;
import com.snowy.biz.modular.comment.param.BizCommentEditParam;
import com.snowy.biz.modular.comment.param.BizCommentIdParam;
import com.snowy.biz.modular.comment.param.BizCommentPageParam;
import com.snowy.biz.modular.comment.service.BizCommentService;

import java.util.List;

/**
 * 评价留言表Service接口实现类
 *
 * @author snowy
 *
 **/
@Service
public class BizCommentServiceImpl extends ServiceImpl<BizCommentMapper, BizComment> implements BizCommentService {

    @Autowired
    private BizTicketOrderService bizOrderService;

    @Autowired
    private BizCommentMapper bizCommentMapper;





    @Override
    public Page<PageComment> page(BizCommentPageParam bizCommentPageParam) {
     Page<PageComment> page = new Page<>(bizCommentPageParam.getCurrent(),bizCommentPageParam.getSize());
        return (Page<PageComment>) bizCommentMapper.select(page,bizCommentPageParam);

    }

    @Override
    public Page<PageComment> pageForC(BizCommentPageParam bizCommentPageParam) {
        Page<PageComment> page = new Page<>(bizCommentPageParam.getCurrent(),bizCommentPageParam.getSize());
        return (Page<PageComment>) bizCommentMapper.selectForC(page,bizCommentPageParam);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizCommentAddParam bizCommentAddParam) {
        BizComment bizComment = BeanUtil.toBean(bizCommentAddParam, BizComment.class);
        bizComment.setAudit(false);
        this.save(bizComment);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizCommentEditParam bizCommentEditParam) {
        BizComment bizComment = this.queryEntity(bizCommentEditParam.getId());
        BeanUtil.copyProperties(bizCommentEditParam, bizComment);
        this.updateById(bizComment);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizCommentIdParam> bizCommentIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizCommentIdParamList, BizCommentIdParam::getId));
    }

    @Override
    public BizComment detail(BizCommentIdParam bizCommentIdParam) {
        return this.queryEntity(bizCommentIdParam.getId());
    }

    @Override
    public BizComment queryEntity(String id) {
        BizComment commentInfo = bizCommentMapper.getCommentInfo(id);

        if(ObjectUtil.isEmpty(commentInfo)) {
            throw new CommonException("评价留言表不存在，id值为：{}", id);
        }
        return commentInfo;
    }

    @Override
    public double getAllSpotsAverageScore() {

        return 3.5;
    }

    @Override
    public int getPriorRatingCount() {
        return 2;
    }

    @Override
    public double getSpotScore(String spotId) {
        Double currentSpotScore =  bizCommentMapper.getSpotScore(spotId);
        return currentSpotScore == null ? 0 : currentSpotScore;
    }

    @Override
    public Integer getRatingCount(String spotId) {
        return bizCommentMapper.getRatingCount(spotId);
    }
}
