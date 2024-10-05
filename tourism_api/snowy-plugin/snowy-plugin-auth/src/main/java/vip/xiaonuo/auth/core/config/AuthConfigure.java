
package vip.xiaonuo.auth.core.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.strategy.SaStrategy;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import vip.xiaonuo.auth.core.enums.SaClientTypeEnum;
import vip.xiaonuo.auth.core.util.StpClientLoginUserUtil;
import vip.xiaonuo.auth.core.util.StpLoginUserUtil;
import vip.xiaonuo.common.pojo.CommonResult;

import javax.annotation.Resource;
import java.util.List;

/**
 * SaToken鉴权配置
 *
 * @author gtc
 * 
 **/
@Configuration
public class AuthConfigure implements WebMvcConfigurer {

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
     *
     * 注解的方式有以下几中，注解既可以加在接口方法上，也可加在Controller类上：
     * 1.@SaCheckLogin: 登录认证 —— 只有登录之后才能进入该方法（常用）
     * 2.@SaCheckRole("admin"): 角色认证 —— 必须具有指定角色标识才能进入该方法（常用）
     * 3.@SaCheckPermission("user:add"): 权限认证 —— 必须具有指定权限才能进入该方法（常用）
     * 4.@SaCheckSafe: 二级认证校验 —— 必须二级认证之后才能进入该方法
     * 5.@SaCheckBasic: HttpBasic认证 —— 只有通过 Basic 认证后才能进入该方法
     *
     * 在Controller中创建一个接口，默认不需要登录也不需要任何权限都可以访问的，只有加了上述注解才会校验
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关，只是说明哪些接口不需要被拦截器拦截，此处都拦截)
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
    }

    @Bean("stpLogic")
    public StpLogic getStpLogic() {
        // 重写Sa-Token的StpLogic，默认客户端类型为B
        return new StpLogic(SaClientTypeEnum.B.getValue());
    }

    @Bean("stpClientLogic")
    public StpLogic getStpClientLogic() {
        // 重写Sa-Token的StpLogic，默认客户端类型为C
        return new StpLogic(SaClientTypeEnum.C.getValue());
    }

    @Bean
    public void rewriteSaStrategy() {
        // 重写Sa-Token的注解处理器，增加注解合并功能
        SaStrategy.me.getAnnotation = AnnotatedElementUtils::getMergedAnnotation;
    }

    /**
     * 权限认证接口实现类，集成权限认证功能
     *
     * @author gtc
     * 
     **/
    @Component
    public static class StpInterfaceImpl implements StpInterface {

        /**
         * 返回一个账号所拥有的权限码集合
         */
        @Override
        public List<String> getPermissionList(Object loginId, String loginType) {
            if (SaClientTypeEnum.B.getValue().equals(loginType)) {
                return StpLoginUserUtil.getLoginUser().getPermissionCodeList();
            } else {
                return StpClientLoginUserUtil.getClientLoginUser().getPermissionCodeList();
            }
        }

        /**
         * 返回一个账号所拥有的角色标识集合
         */
        @Override
        public List<String> getRoleList(Object loginId, String loginType) {
            if (SaClientTypeEnum.B.getValue().equals(loginType)) {
                return StpLoginUserUtil.getLoginUser().getRoleCodeList();
            } else {
                return StpClientLoginUserUtil.getClientLoginUser().getRoleCodeList();
            }
        }
    }

    /**
     * API文档分组配置
     *
     * @author gtc
     * 
     **/
    @Bean(value = "authDocApi")
    public Docket authDocApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("登录鉴权AUTH")
                        .description("登录鉴权AUTH")
                        .termsOfServiceUrl("https://www.xiaonuo.vip")
                        .contact(new Contact("SNOWY_TEAM","https://www.xiaonuo.vip", "xuyuxiang29@foxmail.com"))
                        .version("2.0.0")
                        .build())
                .globalResponseMessage(RequestMethod.GET, CommonResult.responseList())
                .globalResponseMessage(RequestMethod.POST, CommonResult.responseList())
                .groupName("登录鉴权AUTH")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("vip.xiaonuo.auth"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("登录鉴权AUTH"));
    }
}
