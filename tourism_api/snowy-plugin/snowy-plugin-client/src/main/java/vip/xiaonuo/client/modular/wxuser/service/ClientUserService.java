package vip.xiaonuo.client.modular.wxuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import vip.xiaonuo.client.modular.wxuser.entity.ClientUser;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserAddParam;
import vip.xiaonuo.client.modular.wxuser.param.ClientUserLoginParam;

public interface ClientUserService extends IService<ClientUser> {

    String login(ClientUserLoginParam loginParam);

    void add(ClientUserAddParam addParam);

    ClientUser getUserInfo();

}
