
package com.snowy.biz.modular.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snowy.biz.modular.comment.entity.BizComment;
import com.snowy.biz.modular.comment.param.BizCommentAddParam;
import com.snowy.biz.modular.comment.param.BizCommentEditParam;
import com.snowy.biz.modular.comment.param.BizCommentIdParam;
import com.snowy.biz.modular.comment.param.BizCommentPageParam;
import com.snowy.biz.modular.comment.result.PageComment;

import java.util.List;

/**
 * 评价留言表Service接口
 *
 * @author snowy
 *
 **/
public interface BizCommentService extends IService<BizComment> {

    /**
     * 获取评价留言表分页
     *
     * @author snowy
     *
     */
    Page<PageComment> page(BizCommentPageParam bizCommentPageParam);


    Page<PageComment> pageForC(BizCommentPageParam bizCommentPageParam);
    /**
     * 添加评价留言表
     *
     * @author snowy
     *
     */
    void add(BizCommentAddParam bizCommentAddParam);

    /**
     * 编辑评价留言表
     *
     * @author snowy
     *
     */
    void edit(BizCommentEditParam bizCommentEditParam);

    /**
     * 删除评价留言表
     *
     * @author snowy
     *
     */
    void delete(List<BizCommentIdParam> bizCommentIdParamList);

    /**
     * 获取评价留言表详情
     *
     * @author snowy
     *
     */
    BizComment detail(BizCommentIdParam bizCommentIdParam);

    /**
     * 获取评价留言表详情
     *
     * @author snowy
     *
     **/
    BizComment queryEntity(String id);

    double getAllSpotsAverageScore();

    int getPriorRatingCount();

    double getSpotScore(String spotId);

    Integer getRatingCount(String spotId);
}
