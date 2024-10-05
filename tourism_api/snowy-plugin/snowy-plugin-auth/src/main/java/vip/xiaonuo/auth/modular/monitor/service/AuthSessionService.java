
package vip.xiaonuo.auth.modular.monitor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.xiaonuo.auth.modular.monitor.param.AuthExitSessionParam;
import vip.xiaonuo.auth.modular.monitor.param.AuthExitTokenParam;
import vip.xiaonuo.auth.modular.monitor.param.AuthSessionPageParam;
import vip.xiaonuo.auth.modular.monitor.result.AuthSessionAnalysisResult;
import vip.xiaonuo.auth.modular.monitor.result.AuthSessionPageResult;

import java.util.List;

/**
 * 会话治理Service接口
 *
 * @author gtc
 *
 **/
public interface AuthSessionService {

    /**
     * 会话统计
     *
     * @author gtc
     *
     **/
    AuthSessionAnalysisResult analysis();

    /**
     * 查询B端会话
     *
     * @author gtc
     *
     */
    Page<AuthSessionPageResult> pageForB(AuthSessionPageParam authSessionPageParam);


    /**
     * 强退B端会话
     *
     * @author gtc
     *
     */
    void exitSessionForB(List<AuthExitSessionParam> authExitSessionParamList);


    /**
     * 强退B端token
     *
     * @author gtc
     *
     */
    void exitTokenForB(List<AuthExitTokenParam> authExitTokenParamList);


}
