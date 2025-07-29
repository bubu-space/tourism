
package com.snowy.auth.modular.monitor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.snowy.auth.modular.monitor.param.AuthExitSessionParam;
import com.snowy.auth.modular.monitor.param.AuthExitTokenParam;
import com.snowy.auth.modular.monitor.param.AuthSessionPageParam;
import com.snowy.auth.modular.monitor.result.AuthSessionAnalysisResult;
import com.snowy.auth.modular.monitor.result.AuthSessionPageResult;

import java.util.List;

/**
 * 会话治理Service接口
 *
 * @author snowy
 *
 **/
public interface AuthSessionService {

    /**
     * 会话统计
     *
     * @author snowy
     *
     **/
    AuthSessionAnalysisResult analysis();

    /**
     * 查询B端会话
     *
     * @author snowy
     *
     */
    Page<AuthSessionPageResult> pageForB(AuthSessionPageParam authSessionPageParam);


    /**
     * 强退B端会话
     *
     * @author snowy
     *
     */
    void exitSessionForB(List<AuthExitSessionParam> authExitSessionParamList);


    /**
     * 强退B端token
     *
     * @author snowy
     *
     */
    void exitTokenForB(List<AuthExitTokenParam> authExitTokenParamList);


}
