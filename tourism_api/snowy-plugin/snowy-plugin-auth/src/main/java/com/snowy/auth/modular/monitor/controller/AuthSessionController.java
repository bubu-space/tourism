
package com.snowy.auth.modular.monitor.controller;

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
import com.snowy.auth.modular.monitor.param.AuthExitSessionParam;
import com.snowy.auth.modular.monitor.param.AuthExitTokenParam;
import com.snowy.auth.modular.monitor.param.AuthSessionPageParam;
import com.snowy.auth.modular.monitor.result.AuthSessionAnalysisResult;
import com.snowy.auth.modular.monitor.result.AuthSessionPageResult;
import com.snowy.auth.modular.monitor.service.AuthSessionService;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.common.pojo.CommonValidList;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 会话治理控制器
 *
 * @author snowy
 *
 **/
@Api(tags = "会话治理控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 3)
@RestController
@Validated
public class AuthSessionController {

    @Resource
    private AuthSessionService authSessionService;

    /**
     * 会话统计
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("会话统计")
    @GetMapping("/auth/session/analysis")
    public CommonResult<AuthSessionAnalysisResult> analysis() {
        return CommonResult.data(authSessionService.analysis());
    }

    /**
     * 查询B端会话
     *
     * @author snowy
     *
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("查询B端会话")
    @GetMapping("/auth/session/b/page")
    public CommonResult<Page<AuthSessionPageResult>> pageForB(AuthSessionPageParam authSessionPageParam) {
        return CommonResult.data(authSessionService.pageForB(authSessionPageParam));
    }


    /**
     * 强退B端会话
     *
     * @author snowy
     *
     **/
    @ApiOperationSupport(order = 4)
    @ApiOperation("强退B端会话")
    @CommonLog("强退B端会话")
    @PostMapping("/auth/session/b/exit")
    public CommonResult<String> exitSessionForB(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitSessionParam> authExitSessionParamList) {
        authSessionService.exitSessionForB(authExitSessionParamList);
        return CommonResult.ok();
    }


    /**
     * 强退B端token
     *
     * @author snowy
     *
     **/
    @ApiOperationSupport(order = 6)
    @ApiOperation("强退B端token")
    @CommonLog("强退B端token")
    @PostMapping("/auth/token/b/exit")
    public CommonResult<String> exitTokenForB(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitTokenParam> authExitTokenParamList) {
        authSessionService.exitTokenForB(authExitTokenParamList);
        return CommonResult.ok();
    }


}
