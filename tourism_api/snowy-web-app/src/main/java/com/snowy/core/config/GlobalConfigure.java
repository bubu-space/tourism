
package com.snowy.core.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaResponse;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.snowy.auth.core.util.StpClientUtil;
import com.snowy.common.annotation.CommonNoRepeat;
import com.snowy.common.annotation.CommonWrapper;
import com.snowy.common.cache.CommonCacheOperator;
import com.snowy.common.enums.CommonDeleteFlagEnum;
import com.snowy.common.exception.CommonException;
import com.snowy.common.listener.CommonDataChangeEventCenter;
import com.snowy.common.listener.CommonDataChangeListener;
import com.snowy.common.pojo.CommonResult;
import com.snowy.common.pojo.CommonWrapperInterface;
import com.snowy.common.util.CommonTimeFormatUtil;
import com.snowy.core.handler.GlobalExceptionUtil;
import com.snowy.sys.core.enums.SysBuildInEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * Snowy配置
 *
 * @author snowy
 *
 **/
@Configuration
@MapperScan(basePackages = {"com.snowy.**.mapper"})
public class GlobalConfigure implements WebMvcConfigurer {

    private static final String COMMON_REPEAT_SUBMIT_CACHE_KEY = "common-repeatSubmit:";

    @Resource
    private CommonCacheOperator commonCacheOperator;

    /**
     * 无需登录的接口地址集合
     */
    private static final String[] NO_LOGIN_PATH_ARR = {
            /* 主入口 */
            "/",

            /* 静态资源 */
            "/favicon.ico",
            "/doc.html",
            "/webjars/**",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/configuration/ui",
            "/configuration/security",
            "/ureport/**",
            "/druid/**",
            "/images/**",

            /*微信用户登录*/
            "/client/c/user/register",
            "/client/c/user/login",

            "/auth/b/getPicCaptcha",
            "/auth/b/getPhoneValidCode",
            "/auth/b/doLogin",
            "/auth/b/doLoginByPhone",

            /* 三方登录相关 */
            "/auth/third/render",
            "/auth/third/callback",

            /* 系统基础配置 */
            "/dev/config/sysBaseList",

            /* 系统字典树 */
            "/dev/dict/tree",

            /* 文件下载 */
            "/dev/file/download",

            /* 用户个人中心相关 */
            "/sys/userCenter/getPicCaptcha",
            "/sys/userCenter/findPasswordGetPhoneValidCode",
            "/sys/userCenter/findPasswordGetEmailValidCode",
            "/sys/userCenter/findPasswordByPhone",
            "/sys/userCenter/findPasswordByEmail",

            /* 业务相关 */
            "/biz/spot/page",
            "/biz/spot/add",
            "/biz/ticket/add",

            /* 微信小程序首页无需登录 */
            "wx/c/index/spotactivite",
            "wx/c/index/getredstrategy",
            "wx/c/index/getredspot",
            "wx/c/index/getrecommend",
            "wx/c/index/getspotbook",
            "wx/c/index/getrecommend",



    };

    /**
     * 仅超管使用的接口地址集合
     */
    private static final String[] SUPER_PERMISSION_PATH_ARR = {
            "/auth/session/**",
            "/auth/third/page",
            "/client/user/**",
            "/sys/org/**",
            "/sys/position/**",
            "/sys/button/**",
            "/sys/menu/**",
            "/sys/module/**",
            "/sys/role/**",
            "/sys/user/**",
            "/dev/config/**",
            "/dev/dict/**",
            "/dev/email/page",
            "/dev/email/delete",
            "/dev/email/detail",
            "/dev/file/page",
            "/dev/file/list",
            "/dev/file/delete",
            "/dev/file/detail",
            "/dev/job/**",
            "/dev/log/**",
            "/dev/message/page",
            "/dev/message/delete",
            "/dev/message/detail",
            "/dev/monitor/**",
            "/dev/sms/page",
            "/dev/sms/delete",
            "/dev/sms/detail",
            "/gen/basic/**",
            "/gen/config/**",
            "/mobile/menu/**",
            "/mobile/module/**",
    };

    /**
     * B端要排除的，相当于C端要认证的
     */
    private static final String[] CLIENT_USER_PERMISSION_PATH_ARR = {
            "/auth/c/**",
            "/client/c/**",
            "/wx/c/**",
    };

    /**
     * 注册跨域过滤器
     */
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                // 指定拦截路由
                .addInclude("/**")

                // 设置鉴权的接口
                .setAuth(r -> {
                    // B端的接口校验B端登录
                    SaRouter.match("/**")
                            // 排除无需登录接口
                            .notMatch(CollectionUtil.newArrayList(NO_LOGIN_PATH_ARR))
                            // 排除C端认证接口
                            .notMatch(CollectionUtil.newArrayList(CLIENT_USER_PERMISSION_PATH_ARR))
                            // 校验B端登录
                            .check(r1 -> StpUtil.checkLogin());

                    // C端的接口校验C端登录
                    SaRouter.match("/**")
                            // 排除无需登录接口
                            .notMatch(CollectionUtil.newArrayList(NO_LOGIN_PATH_ARR))
                            // 匹配C端认证接口
                            .match(CollectionUtil.newArrayList(CLIENT_USER_PERMISSION_PATH_ARR))
                            // 校验C端登录
                            .check(r1 -> StpClientUtil.checkLogin());

                    // B端的超管接口校验B端超管角色
                    SaRouter.match("/**")
                            // 排除无需登录接口
                            .notMatch(CollectionUtil.newArrayList(NO_LOGIN_PATH_ARR))
                            // 匹配超管接口
                            .match(CollectionUtil.newArrayList(SUPER_PERMISSION_PATH_ARR))
                            // 校验B端超管角色
                            .check(r1 -> StpUtil.checkRole(SysBuildInEnum.BUILD_IN_ROLE_CODE.getValue()));
                })

                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {

                    // ---------- 设置跨域响应头 ----------
                    SaHolder.getResponse()

                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            // .setHeader("X-Frame-Options", "SAMEORIGIN")

                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*");

                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            // OPTIONS预检请求，不做处理
                            .free(r -> {})
                            .back();
                })

                // 异常处理
                .setError(e -> {
                    // 由于过滤器中抛出的异常不进入全局异常处理，所以必须提供[异常处理函数]来处理[认证函数]里抛出的异常
                    // 在[异常处理函数]里的返回值，将作为字符串输出到前端，此处统一转为JSON输出前端
                    SaResponse saResponse = SaHolder.getResponse();
                    saResponse.setHeader(Header.CONTENT_TYPE.getValue(), ContentType.JSON + ";charset=" +  CharsetUtil.UTF_8);
                    return GlobalExceptionUtil.getCommonResult((Exception) e);
                });
    }

    /**
     * RedisTemplate序列化
     *
     * @author snowy
     *
     **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 静态资源映射
     *
     * @author snowy
     *
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 添加节流防抖拦截器
     *
     * @author snowy
     *
     **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                     @NonNull Object handler) throws Exception {
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Method method = handlerMethod.getMethod();
                    CommonNoRepeat annotation = method.getAnnotation(CommonNoRepeat.class);
                    if (ObjectUtil.isNotEmpty(annotation)) {
                        JSONObject repeatSubmitJsonObject = this.isRepeatSubmit(request, annotation);
                        if (repeatSubmitJsonObject.getBool("repeat")) {
                            response.setCharacterEncoding(CharsetUtil.UTF_8);
                            response.setContentType(ContentType.JSON.toString());
                            response.getWriter().write(JSONUtil.toJsonStr(CommonResult.error("请求过于频繁，请" + repeatSubmitJsonObject.getStr("time") + "后再试")));
                            return false;
                        }
                    }
                }
                return true;
            }

            public JSONObject isRepeatSubmit(HttpServletRequest request, CommonNoRepeat annotation) {
                JSONObject jsonObject = JSONUtil.createObj();
                jsonObject.set("repeatParam", JSONUtil.toJsonStr(request.getParameterMap()));
                jsonObject.set("repeatTime", DateUtil.current());
                String url = request.getRequestURI();
                // 获取该接口缓存的限流数据
                Object cacheObj = commonCacheOperator.get(COMMON_REPEAT_SUBMIT_CACHE_KEY + url);
                if (ObjectUtil.isNotEmpty(cacheObj)) {
                    JSONObject cacheJsonObject = JSONUtil.parseObj(cacheObj);
                    if(cacheJsonObject.containsKey(url)) {
                        JSONObject existRepeatJsonObject = cacheJsonObject.getJSONObject(url);
                        // 如果与上次参数一致，且时间间隔小于要求的限流时长，则判定为重复提交
                        if (jsonObject.getStr("repeatParam").equals(existRepeatJsonObject.getStr("repeatParam"))) {
                            long interval = jsonObject.getLong("repeatTime") - existRepeatJsonObject.getLong("repeatTime");
                            if(interval < annotation.interval()) {
                                long secondsParam = (annotation.interval() - interval) / 1000;
                                if(secondsParam == 0) {
                                    return JSONUtil.createObj().set("repeat", false);
                                } else {
                                    return JSONUtil.createObj().set("repeat", true).set("time", CommonTimeFormatUtil.formatSeconds(secondsParam));
                                }
                            }
                        }
                    }
                }
                // 缓存最新的该接口的限流数据，为防止缓存的数据过多，缓存时效为1小时
                commonCacheOperator.put(COMMON_REPEAT_SUBMIT_CACHE_KEY + url, JSONUtil.createObj().set(url, jsonObject), 60 * 60);
                return JSONUtil.createObj().set("repeat", false);
            }
        }).addPathPatterns("/**");
    }

    /**
     * 通用Wrapper的AOP
     *
     * @author snowy
     *
     */
    @Component
    @Aspect
    public static class CommonWrapperAop {

        /**
         * 切入点
         *
         * @author snowy
         *
         */
        @Pointcut("@annotation(com.snowy.common.annotation.CommonWrapper)")
        private void wrapperPointcut() {

        }

        /**
         * 执行包装
         *
         * @author snowy
         *
         */
        @Around("wrapperPointcut()")
        public Object doWrapper(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            // 直接执行原有业务逻辑
            Object proceedResult = proceedingJoinPoint.proceed();
            return processWrapping(proceedingJoinPoint, proceedResult);
        }

        /**
         * 具体包装过程
         *
         * @author snowy
         *
         */
        @SuppressWarnings("all")
        private Object processWrapping(ProceedingJoinPoint proceedingJoinPoint, Object originResult) throws IllegalAccessException, InstantiationException {
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            CommonWrapper commonWrapper = method.getAnnotation(CommonWrapper.class);
            Class<? extends CommonWrapperInterface<?>>[] baseWrapperClasses = commonWrapper.value();
            if (ObjectUtil.isEmpty(baseWrapperClasses)) {
                return originResult;
            }
            if (!(originResult instanceof CommonResult)) {
                return originResult;
            }
            CommonResult commonResult = (CommonResult) originResult;
            Object beWrapped = commonResult.getData();
            if (ObjectUtil.isBasicType(beWrapped)) {
                throw new CommonException("被包装的值不能是基本类型");
            }
            if (beWrapped instanceof Page) {
                Page page = (Page) beWrapped;
                ArrayList<Map<String, Object>> maps = new ArrayList<>();
                for (Object wrappedItem : page.getRecords()) {
                    maps.add(this.wrapPureObject(wrappedItem, baseWrapperClasses));
                }
                page.setRecords(maps);
                commonResult.setData(page);
            } else if (beWrapped instanceof Collection) {
                Collection collection = (Collection) beWrapped;
                List<Map<String, Object>> maps = new ArrayList<>();
                for (Object wrappedItem : collection) {
                    maps.add(this.wrapPureObject(wrappedItem, baseWrapperClasses));
                }
                commonResult.setData(maps);
            } else if (ArrayUtil.isArray(beWrapped)) {
                Object[] objects = this.objToArray(beWrapped);
                ArrayList<Map<String, Object>> maps = new ArrayList<>();
                for (Object wrappedItem : objects) {
                    maps.add(this.wrapPureObject(wrappedItem, baseWrapperClasses));
                }
                commonResult.setData(maps);
            } else {
                commonResult.setData(this.wrapPureObject(beWrapped, baseWrapperClasses));
            }
            return commonResult;
        }

        /**
         * 原始对象包装JSONObject
         *
         * @author snowy
         *
         */
        @SuppressWarnings("all")
        private JSONObject wrapPureObject(Object originModel, Class<? extends CommonWrapperInterface<?>>[] baseWrapperClasses) {
            JSONObject jsonObject = JSONUtil.parseObj(originModel);
            try {
                for (Class<? extends CommonWrapperInterface<?>> commonWrapperClass : baseWrapperClasses) {
                    CommonWrapperInterface commonWrapperInterface = commonWrapperClass.newInstance();
                    Map<String, Object> incrementFieldsMap = commonWrapperInterface.doWrap(originModel);
                    jsonObject.putAll(incrementFieldsMap);
                }
            } catch (Exception e) {
                throw new CommonException("原始对象包装过程，字段转化异常：{}", e.getMessage());
            }
            return jsonObject;
        }

        /**
         * Object转array
         *
         * @author snowy
         *
         */
        private Object[] objToArray(Object object) {
            int length = Array.getLength(object);
            Object[] result = new Object[length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Array.get(object, i);
            }
            return result;
        }
    }

    /**
     * 分页插件
     *
     * @author snowy
     *
     **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    /**
     * 数据库id选择器，用于Mapper.xml中
     * MyBatis可以根据不同的数据库厂商执行不同的语句
     *
     * @author snowy
     *
     */
    @Component
    public static class CustomDbIdProvider implements DatabaseIdProvider {

        @Override
        public String getDatabaseId(DataSource dataSource) throws SQLException {
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
                String url = conn.getMetaData().getURL().toLowerCase();
                if (url.contains("jdbc:oracle")) {
                    return "oracle";
                } else if (url.contains("jdbc:postgresql")) {
                    return "pgsql";
                } else if (url.contains("jdbc:mysql")) {
                    return "mysql";
                } else if (url.contains("jdbc:dm")) {
                    return "dm";
                } else if (url.contains("jdbc:kingbase")) {
                    return "kingbase";
                }  else {
                    return "mysql";
                }
            } finally {
                JdbcUtils.closeConnection(conn);
            }
        }
    }

    /**
     * 自定义公共字段自动注入
     *
     * @author snowy
     *
     */
    @Component
    public static class CustomMetaObjectHandler implements MetaObjectHandler {

        /** 删除标志 */
        private static final String DELETE_FLAG = "deleteFlag";

        /** 创建人 */
        private static final String CREATE_USER = "createUser";

        /** 创建时间 */
        private static final String CREATE_TIME = "createTime";

        /** 更新人 */
        private static final String UPDATE_USER = "updateUser";

        /** 更新时间 */
        private static final String UPDATE_TIME = "updateTime";

        @Override
        public void insertFill(MetaObject metaObject) {
            try {
                //为空则设置deleteFlag
                Object deleteFlag = metaObject.getValue(DELETE_FLAG);
                if (ObjectUtil.isNull(deleteFlag)) {
                    setFieldValByName(DELETE_FLAG, EnumUtil.toString(CommonDeleteFlagEnum.NOT_DELETE), metaObject);
                }
            } catch (ReflectionException ignored) { }
            try {
                //为空则设置createUser
                Object createUser = metaObject.getValue(CREATE_USER);
                if (ObjectUtil.isNull(createUser)) {
                    setFieldValByName(CREATE_USER, this.getUserId(), metaObject);
                }
            } catch (ReflectionException ignored) { }
            try {
                //为空则设置createTime
                Object createTime = metaObject.getValue(CREATE_TIME);
                if (ObjectUtil.isNull(createTime)) {
                    setFieldValByName(CREATE_TIME, DateTime.now(), metaObject);
                }
            } catch (ReflectionException ignored) { }
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            try {
                //设置updateUser
                setFieldValByName(UPDATE_USER, this.getUserId(), metaObject);
            } catch (ReflectionException ignored) { }
            try {
                //设置updateTime
                setFieldValByName(UPDATE_TIME, DateTime.now(), metaObject);
            } catch (ReflectionException ignored) { }
        }

        /**
         * 获取用户id
         */
        private String getUserId() {
            try {
                String loginId = StpUtil.getLoginIdAsString();
                if (ObjectUtil.isNotEmpty(loginId)) {
                    return loginId;
                } else {
                    return "-1";
                }
            } catch (Exception e) {
                return "-1";
            }

        }
    }

    /**
     * 注册数据变化事件中心 事件发布器
     *
     * @author snowy
     *
     **/
    @Resource
    public void registerListenerList(List<CommonDataChangeListener> dataChangeListenerList) {
        CommonDataChangeEventCenter.registerListenerList(dataChangeListenerList);
    }
}
