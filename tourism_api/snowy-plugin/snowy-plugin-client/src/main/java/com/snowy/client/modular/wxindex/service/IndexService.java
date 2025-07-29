package com.snowy.client.modular.wxindex.service;

import org.springframework.stereotype.Service;
import com.snowy.biz.modular.activitie.entity.BizActivitie;
import com.snowy.client.modular.wxindex.result.WxIndexSpotActivite;

import java.util.List;

@Service
public interface IndexService {

    List<WxIndexSpotActivite> getSpotActivite(String title);

}
