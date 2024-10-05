
package vip.xiaonuo.biz.modular.comment.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.xiaonuo.biz.modular.comment.result.PageComment;
import vip.xiaonuo.biz.modular.order.service.BizTicketOrderService;
import vip.xiaonuo.common.exception.CommonException;
import vip.xiaonuo.common.page.CommonPageRequest;
import vip.xiaonuo.biz.modular.comment.entity.BizComment;
import vip.xiaonuo.biz.modular.comment.mapper.BizCommentMapper;
import vip.xiaonuo.biz.modular.comment.param.BizCommentAddParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentEditParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentIdParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentPageParam;
import vip.xiaonuo.biz.modular.comment.service.BizCommentService;

import java.util.List;

/**
 * 评价留言表Service接口实现类
 *
 * @author gtc
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
//        QueryWrapper<BizComment> queryWrapper = new QueryWrapper<>();
//
//        if(ObjectUtil.isNotEmpty(bizCommentPageParam.getOrderId())) {
//            queryWrapper.lambda().eq(BizComment::getOrderId, bizCommentPageParam.getOrderId());
//        }
//        if(ObjectUtil.isAllNotEmpty(bizCommentPageParam.getSortField(), bizCommentPageParam.getSortOrder())) {
//            CommonSortOrderEnum.validate(bizCommentPageParam.getSortOrder());
//            queryWrapper.orderBy(true, bizCommentPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
//                    StrUtil.toUnderlineCase(bizCommentPageParam.getSortField()));
//        } else {
//            queryWrapper.lambda().orderByAsc(BizComment::getId);
//        }
//        Page<PageComment> page = CommonPageRequest.defaultPage();
//        IPage<PageComment> select = bizCommentMapper.select(page, bizCommentPageParam);
//
//        return new Page<PageComment>(select.getCurrent(), select.getSize(), select.getTotal()).setRecords(select.getRecords());
        Page<PageComment> page = new Page<>(bizCommentPageParam.getCurrent(),bizCommentPageParam.getSize());
        return (Page<PageComment>) bizCommentMapper.select(page,bizCommentPageParam);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizCommentAddParam bizCommentAddParam) {
        BizComment bizComment = BeanUtil.toBean(bizCommentAddParam, BizComment.class);
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
