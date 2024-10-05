package vip.xiaonuo.client.modular.wxuser.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.xiaonuo.auth.core.util.StpClientUtil;
import vip.xiaonuo.client.modular.wxuser.entity.ClientUser;
import vip.xiaonuo.client.modular.wxuser.enums.ClientUserStatusEnum;
import vip.xiaonuo.client.modular.wxuser.mapper.ClientUserMapper;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserAddParam;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserLoginParam;
import vip.xiaonuo.client.modular.wxuser.service.ClientUserService;
import vip.xiaonuo.common.exception.CommonException;


@Service
public class ClientUserServiceimpl extends ServiceImpl<ClientUserMapper, ClientUser> implements ClientUserService {

    @Override
    public String login(ClientUserLoginParam loginParam) {
        LambdaQueryWrapper<ClientUser> wrapper = new LambdaQueryWrapper<ClientUser>().eq(ClientUser::getPhone, loginParam.getPhone());
        ClientUser user = this.getOne(wrapper);//查询用户是否存在
        if (ObjectUtil.isNull(user)){
            throw new CommonException("用户不存在");
        }
        if (ObjectUtil.notEqual(user.getPassword(),loginParam.getPassword())){
            throw new CommonException("密码不正确");
        }
        if (ObjectUtil.equal(ClientUserStatusEnum.DISABLED.getValue(),user.getStatus())){
            throw new CommonException("账户已被封禁");
        }
        //利用sa-token完成登陆
        StpClientUtil.login(user.getId(), new SaLoginModel().setDevice("MINI").setExtra("id", user.getId()));
        // 缓存用户信息，此处使用TokenSession为了指定时间内无操作则自动下线
        StpClientUtil.getTokenSession().set("clientLoginUser", user);
        StpClientUtil.getSession().set("wxuser", JSONUtil.toJsonStr(user));
        return StpClientUtil.getTokenInfo().tokenValue;
    }


    @Override
    public ClientUser getUserInfo() {
        String userId = StpClientUtil.getLoginIdAsString();
        return this.getById(userId);
    }

    @Override
    public void add(ClientUserAddParam addParam) {
        ClientUser user = BeanUtil.copyProperties(addParam, ClientUser.class);
        LambdaQueryWrapper<ClientUser> clientUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clientUserLambdaQueryWrapper.eq(ClientUser::getPhone, user.getPhone());
        ClientUser one = this.getOne(clientUserLambdaQueryWrapper);
        if (ObjectUtil.isNotNull(one)){
            throw new CommonException("手机号已被注册");
        }
        user.setStatus(ClientUserStatusEnum.ENABLE.getValue());
        this.save(user);
    }
}
