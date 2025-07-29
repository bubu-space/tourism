
package com.snowy.biz.modular.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import com.snowy.biz.modular.comment.entity.BizComment;
import com.snowy.biz.modular.comment.param.BizCommentPageParam;
import com.snowy.biz.modular.comment.result.PageComment;

/**
 * 评价留言表Mapper接口
 *
 * @author snowy
 *
 **/
public interface BizCommentMapper extends BaseMapper<BizComment> {
    IPage<PageComment> select(Page<PageComment> page,BizCommentPageParam bizCommentPageParam);

    IPage<PageComment> selectForC(Page<PageComment> page,BizCommentPageParam bizCommentPageParam);

    BizComment getCommentInfo(String id);

    Double getSpotScore(String spotId);

    Integer getRatingCount(String spotId);
}
