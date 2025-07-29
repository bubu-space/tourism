package com.snowy.client.modular.wxuser.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.snowy.common.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.snowy.client.modular.wxuser.entity.ClientUser;
import com.snowy.client.modular.wxuser.param.ClientUserAddParam;
import com.snowy.client.modular.wxuser.param.ClientUserEditParam;
import com.snowy.client.modular.wxuser.param.ClientUserLoginParam;
import com.snowy.client.modular.wxuser.service.ClientUserService;
import com.snowy.common.annotation.CommonLog;
import com.snowy.common.pojo.CommonResult;
import com.snowy.dev.api.DevConfigApi;
import com.snowy.dev.modular.file.service.DevFileService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(tags = "微信用户控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 2)
@RequestMapping("/client/c/user")
@Validated
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
//        String regex = "^1[3-9]\\d{9}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(addParam.getPhone());
//        boolean pass = matcher.matches();
//        if (!pass){
//            throw new CommonException("手机号格式不正确");
//        }
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
    public CommonResult<String> updateUserInfo(@RequestBody @Valid ClientUserEditParam addParam){
        ClientUser user = BeanUtil.copyProperties(addParam, ClientUser.class);
        service.updateById(user);
        return CommonResult.ok();
    }

}
