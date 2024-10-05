
package vip.xiaonuo.auth.modular.login.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.auth.core.enums.SaClientTypeEnum;
import vip.xiaonuo.auth.core.pojo.SaBaseLoginUser;
import vip.xiaonuo.auth.modular.login.param.AuthAccountPasswordLoginParam;
import vip.xiaonuo.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import vip.xiaonuo.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import vip.xiaonuo.auth.modular.login.result.AuthPicValidCodeResult;
import vip.xiaonuo.auth.modular.login.service.AuthService;
import vip.xiaonuo.common.pojo.CommonResult;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * B端登录控制器
 *
 * @author gtc
 * 
 */
@Api(tags = "B端登录控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 2)
@RestController
@Validated
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * B端获取图片验证码
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 1)
    @ApiOperation("B端获取图片验证码")
    @GetMapping("/auth/b/getPicCaptcha")
    public CommonResult<AuthPicValidCodeResult> getPicCaptcha() {
        return CommonResult.data(authService.getPicCaptcha(SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端获取手机验证码
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 2)
    @ApiOperation("B端获取手机验证码")
    @GetMapping("/auth/b/getPhoneValidCode")
    public CommonResult<String> getPhoneValidCode(@Valid AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam) {
        return CommonResult.data(authService.getPhoneValidCode(authGetPhoneValidCodeParam, SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端账号密码登录
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 3)
    @ApiOperation("B端账号密码登录")
    @PostMapping("/auth/b/doLogin")
    public CommonResult<String> doLogin(@RequestBody @Valid AuthAccountPasswordLoginParam authAccountPasswordLoginParam) {
        return CommonResult.data(authService.doLogin(authAccountPasswordLoginParam, SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端手机验证码登录
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 4)
    @ApiOperation("B端手机验证码登录")
    @PostMapping("/auth/b/doLoginByPhone")
    public CommonResult<String> doLoginByPhone(@RequestBody @Valid AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByPhone(authPhoneValidCodeLoginParam, SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端退出
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 5)
    @ApiOperation("B端退出")
    @SaCheckLogin
    @GetMapping("/auth/b/doLogout")
    public CommonResult<String> doLogout() {
        StpUtil.logout();
        return CommonResult.ok();
    }

    /**
     * B端获取用户信息
     *
     * @author gtc
     * 
     **/
    @ApiOperationSupport(order = 6)
    @ApiOperation("B端获取用户信息")
    @SaCheckLogin
    @GetMapping("/auth/b/getLoginUser")
    public CommonResult<SaBaseLoginUser> getLoginUser() {
        return CommonResult.data(authService.getLoginUser());
    }
}
