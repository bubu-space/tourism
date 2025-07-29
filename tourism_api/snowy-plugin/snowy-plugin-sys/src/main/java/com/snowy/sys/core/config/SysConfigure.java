
package com.snowy.sys.core.config;

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
import com.snowy.common.pojo.CommonResult;

import javax.annotation.Resource;

/**
 * 系统功能相关配置
 *
 * @author snowy
 *
 **/
@Configuration
public class SysConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author snowy
     *
     **/
    @Bean(value = "sysDocApi")
    public Docket sysDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("系统功能SYS")
                        .description("系统功能SYS")
                        .termsOfServiceUrl("https://www.snowy.com")
                        .contact(new Contact("SNOWY_TEAM","https://www.snowy.com", "xuyuxiang29@foxmail.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("系统功能SYS")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.snowy.sys"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("系统功能SYS"));
    }
}
