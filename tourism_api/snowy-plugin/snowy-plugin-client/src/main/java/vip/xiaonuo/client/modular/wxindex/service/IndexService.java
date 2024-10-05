package vip.xiaonuo.client.modular.wxindex.service;

import org.springframework.stereotype.Service;
import vip.xiaonuo.biz.modular.activitie.entity.BizActivitie;
import vip.xiaonuo.client.modular.wxindex.result.WxIndexSpotActivite;

import java.util.List;

@Service
public interface IndexService {

    List<WxIndexSpotActivite> getSpotActivite(String title);

}
