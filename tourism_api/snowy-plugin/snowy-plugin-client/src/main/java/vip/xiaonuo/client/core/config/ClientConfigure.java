
package vip.xiaonuo.client.core.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * C端相关配置
 *
 * @author gtc
 *
 **/
@Configuration
public class ClientConfigure {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * API文档分组配置
     *
     * @author gtc
     *
     **/
    @Bean(value = "clientDocApi")
    public Docket clientDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("C端功能CLIENT")
                        .description("C端功能CLIENT")
                        .termsOfServiceUrl("https://www.xiaonuo.vip")
                        .contact(new Contact("SNOWY_TEAM","https://www.xiaonuo.vip", "xuyuxiang29@foxmail.com"))
                        .version("2.0.0")
                        .build())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("C端功能CLIENT")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("vip.xiaonuo.client"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("C端功能CLIENT"));
    }


    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat() {
        return jacksonObjectMapperBuilder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer(formatter);
            LocalDateTimeSerializer serializer = new LocalDateTimeSerializer(formatter);
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, serializer);
            jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class, deserializer);
        };
    }
}
