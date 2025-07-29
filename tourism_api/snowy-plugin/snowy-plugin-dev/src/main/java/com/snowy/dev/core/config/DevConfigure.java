
package com.snowy.dev.core.config;

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
 * 开发工具相关配置
 *
 * @author snowy
 *
 **/
@Configuration
public class DevConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author snowy
     *
     **/
    @Bean(value = "devDocApi")
    public Docket devDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("开发工具DEV")
                        .description("开发工具DEV")
                        .termsOfServiceUrl("https://www.snowy.com")
                        .contact(new Contact("SNOWY_TEAM","https://www.snowy.com", "xuyuxiang29@foxmail.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("开发工具DEV")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.snowy.dev"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("开发工具DEV"));
    }
}
