
package vip.xiaonuo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Slf4j
@EnableSwagger2WebMvc
@RestController
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
public class Application {

    /* 解决druid 日志报错：discard long time none received connection:xxx */
    static {
        System.setProperty("druid.mysql.usePingMethod","false");
    }

    /**
     * 主启动函数
     *
     * @author gtc
     * 
     */
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
        log.info(">>> {}", Application.class.getSimpleName().toUpperCase() + " STARTING SUCCESS");
    }

    /**
     * 首页
     *
     * @author gtc
     * 
     **/
    @GetMapping("/")
    public String index() {
        return "WELCOME";
    }
}
