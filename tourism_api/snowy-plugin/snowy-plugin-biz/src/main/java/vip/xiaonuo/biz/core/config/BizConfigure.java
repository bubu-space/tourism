
package vip.xiaonuo.biz.core.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import vip.xiaonuo.common.pojo.CommonResult;

import javax.annotation.Resource;

/**
 * 业务相关配置
 *
 * @author gtc
 * 
 **/
@Configuration
public class BizConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author gtc
     * 
     **/
    @Bean(value = "bizDocApi")
    public Docket bizDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("业务功能BIZ")
                        .description("业务功能BIZ")
                        .termsOfServiceUrl("https://www.xiaonuo.vip")
                        .contact(new Contact("SNOWY_TEAM","https://www.xiaonuo.vip", "xuyuxiang29@foxmail.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("业务功能BIZ")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("vip.xiaonuo.biz"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("业务功能BIZ"));
    }
}
