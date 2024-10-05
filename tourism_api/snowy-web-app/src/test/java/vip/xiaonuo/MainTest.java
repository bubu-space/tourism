
package vip.xiaonuo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vip.xiaonuo.biz.modular.dashboard.result.DashBoard;
import vip.xiaonuo.biz.modular.dashboard.service.DashboardService;
import vip.xiaonuo.client.modular.wxorder.controller.OrederController;
import vip.xiaonuo.client.modular.wxplan.entity.Content;
import vip.xiaonuo.client.modular.wxplan.service.WxplanService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 * 主测试类
 *
 * @author gtc
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MainTest {
    @Autowired
    private WxplanService wxplanService;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private OrederController orederController;
    @Test
    public void test() {
        wxplanService.lambdaQuery().list().stream().forEach(plan -> {
            List<Content> contentList = plan.getContent();
            if (contentList.isEmpty()) {
                return;
            }

            // 找到最早的Content时间
            LocalDateTime latestDateTime = contentList.stream()
                    .max(Comparator.comparing(Content::getDateTime))
                    .map(Content::getDateTime)
                    .orElse(LocalDateTime.MIN);

            // 如果最早的Content时间晚于当前时间，则标记为未完成
            if (LocalDateTime.now().isAfter(latestDateTime)) {
                plan.setStatus("已完成完成");
                wxplanService.updateById(plan);
            }
        });


    }


}
