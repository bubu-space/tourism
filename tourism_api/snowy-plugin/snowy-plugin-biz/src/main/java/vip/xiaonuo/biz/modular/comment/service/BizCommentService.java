
package vip.xiaonuo.biz.modular.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.xiaonuo.biz.modular.comment.entity.BizComment;
import vip.xiaonuo.biz.modular.comment.param.BizCommentAddParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentEditParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentIdParam;
import vip.xiaonuo.biz.modular.comment.param.BizCommentPageParam;
import vip.xiaonuo.biz.modular.comment.result.PageComment;

import java.util.List;

/**
 * 评价留言表Service接口
 *
 * @author gtc
 *
 **/
public interface BizCommentService extends IService<BizComment> {

    /**
     * 获取评价留言表分页
     *
     * @author gtc
     *
     */
    Page<PageComment> page(BizCommentPageParam bizCommentPageParam);

    /**
     * 添加评价留言表
     *
     * @author gtc
     *
     */
    void add(BizCommentAddParam bizCommentAddParam);

    /**
     * 编辑评价留言表
     *
     * @author gtc
     *
     */
    void edit(BizCommentEditParam bizCommentEditParam);

    /**
     * 删除评价留言表
     *
     * @author gtc
     *
     */
    void delete(List<BizCommentIdParam> bizCommentIdParamList);

    /**
     * 获取评价留言表详情
     *
     * @author gtc
     *
     */
    BizComment detail(BizCommentIdParam bizCommentIdParam);

    /**
     * 获取评价留言表详情
     *
     * @author gtc
     *
     **/
    BizComment queryEntity(String id);

    double getAllSpotsAverageScore();

    int getPriorRatingCount();

    double getSpotScore(String spotId);

    Integer getRatingCount(String spotId);
}
