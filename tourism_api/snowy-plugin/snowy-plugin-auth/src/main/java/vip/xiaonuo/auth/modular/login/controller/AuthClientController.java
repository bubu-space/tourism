
package vip.xiaonuo.auth.modular.login.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.xiaonuo.auth.core.annotation.SaClientCheckLogin;
import vip.xiaonuo.auth.core.enums.SaClientTypeEnum;
import vip.xiaonuo.auth.core.pojo.SaBaseClientLoginUser;
import vip.xiaonuo.auth.core.util.StpClientUtil;
import vip.xiaonuo.auth.modular.login.param.AuthAccountPasswordLoginParam;
import vip.xiaonuo.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import vip.xiaonuo.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import vip.xiaonuo.auth.modular.login.result.AuthPicValidCodeResult;
import vip.xiaonuo.auth.modular.login.service.AuthService;
import vip.xiaonuo.common.pojo.CommonResult;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * C端登录控制器
 *
 * @author gtc
 *
 */
@Api(tags = "C端登录控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 1)
@RestController
@Validated
public class AuthClientController {

    @Resource
    private AuthService authService;

    /**
     * C端获取图片验证码
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 1)
    @ApiOperation("C端获取图片验证码")
    @GetMapping("/auth/c/getPicCaptcha")
    public CommonResult<AuthPicValidCodeResult> getPicCaptcha() {
        return CommonResult.data(authService.getPicCaptcha(SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端获取手机验证码
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 2)
    @ApiOperation("C端获取手机验证码")
    @GetMapping("/auth/c/getPhoneValidCode")
    public CommonResult<String> getPhoneValidCode(@Valid AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam) {
        return CommonResult.data(authService.getPhoneValidCode(authGetPhoneValidCodeParam, SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端账号密码登录
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 3)
    @ApiOperation("C端账号密码登录")
    @PostMapping("/auth/c/doLogin")
    public CommonResult<String> doLogin(@RequestBody @Valid AuthAccountPasswordLoginParam authAccountPasswordLoginParam) {
        return CommonResult.data(authService.doLogin(authAccountPasswordLoginParam, SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端手机验证码登录
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 4)
    @ApiOperation("C端手机验证码登录")
    @PostMapping("/auth/c/doLoginByPhone")
    public CommonResult<String> doLoginByPhone(@RequestBody @Valid AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByPhone(authPhoneValidCodeLoginParam, SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端退出
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 5)
    @ApiOperation("C端退出")
    @SaClientCheckLogin
    @GetMapping("/auth/c/doLogout")
    public CommonResult<String> doLogout() {
        StpClientUtil.logout();
        return CommonResult.ok();
    }

    /**
     * C端获取用户信息
     *
     * @author gtc
     *
     **/
    @ApiOperationSupport(order = 6)
    @ApiOperation("C端获取用户信息")
    @SaClientCheckLogin
    @GetMapping("/auth/c/getLoginUser")
    public CommonResult<SaBaseClientLoginUser> getLoginUser() {
        return CommonResult.data(authService.getClientLoginUser());
    }
}
