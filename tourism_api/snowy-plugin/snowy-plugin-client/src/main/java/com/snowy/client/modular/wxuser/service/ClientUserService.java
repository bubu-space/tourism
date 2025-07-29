package com.snowy.client.modular.wxuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import com.snowy.client.modular.wxuser.entity.ClientUser;
import com.snowy.client.modular.wxuser.param.ClientUserAddParam;
import com.snowy.client.modular.wxuser.param.ClientUserLoginParam;

public interface ClientUserService extends IService<ClientUser> {

    String login(ClientUserLoginParam loginParam);

    void add(ClientUserAddParam addParam);

    ClientUser getUserInfo();

}
