package vip.xiaonuo.client.modular.wxuser.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.xiaonuo.client.modular.wxuser.entity.ClientUser;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserAddParam;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserLoginParam;
import vip.xiaonuo.client.modular.wxuser.service.ClientUserService;
import vip.xiaonuo.common.annotation.CommonLog;
import vip.xiaonuo.common.pojo.CommonResult;
import vip.xiaonuo.dev.api.DevConfigApi;
import vip.xiaonuo.dev.modular.file.service.DevFileService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Api(tags = "微信用户控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 2)
@RequestMapping("/client/c/user")
public class ClientUserController {
    @Resource
    private ClientUserService service;

    @Resource
    private DevFileService devFileService;

    @Resource
    private DevConfigApi devConfigApi;

    /** 默认文件引擎 */
    private static final String SNOWY_SYS_DEFAULT_FILE_ENGINE_KEY = "SNOWY_SYS_DEFAULT_FILE_ENGINE";

    @ApiOperationSupport(order = 2)
    @ApiOperation("上传头像")
    @CommonLog("上传头像返回url")
    @PostMapping("/uploadAvatar")
    public CommonResult<String> uploadDynamicReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(devConfigApi.getValueByKey(SNOWY_SYS_DEFAULT_FILE_ENGINE_KEY), file));
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody @Valid ClientUserLoginParam loginParam){
        return CommonResult.data(service.login(loginParam));
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public CommonResult<String> add(@RequestBody @Valid ClientUserAddParam addParam){
        service.add(addParam);
        return CommonResult.ok();
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public CommonResult<String> logout(){
        StpUtil.logout();
        return CommonResult.ok();
    }


    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public CommonResult<ClientUser> getUserInfo(){
        return CommonResult.data(service.getUserInfo());
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/updateUserInfo")
    public CommonResult<String> updateUserInfo(@RequestBody @Valid ClientUserAddParam addParam){
        ClientUser user = BeanUtil.copyProperties(addParam, ClientUser.class);
        service.updateById(user);
        return CommonResult.ok();
    }

}
