package novel.learn.myNovel.core.config;

import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.ApiRouterConsts;
import novel.learn.myNovel.core.constant.SystemConfigConsts;
import novel.learn.myNovel.core.interceptor.AuthInterceptor;
import novel.learn.myNovel.core.interceptor.FileInterceptor;
import novel.learn.myNovel.core.interceptor.FlowLimitInterceptor;
import novel.learn.myNovel.core.interceptor.TokenParseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器，xss过滤器之后到达这里进行拦截
 * */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final FlowLimitInterceptor flowLimitInterceptor;

    private final AuthInterceptor authInterceptor;

    private final FileInterceptor fileInterceptor;

    private final TokenParseInterceptor tokenParseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 流量限制拦截器
        registry.addInterceptor(flowLimitInterceptor)
                //所有接口都要通过这个拦截器
                .addPathPatterns("/**")
                .order(0);

        // 文件访问拦截
        registry.addInterceptor(fileInterceptor)
                //这个拦截器会拦截以下的接口
                .addPathPatterns(SystemConfigConsts.STATIC_RESOURCE + "**",
                        SystemConfigConsts.IMAGE_UPLOAD_DIRECTORY + "**",
                        SystemConfigConsts.STATIC_RESOURCE_IMAGES + "**")
                .order(1);

        // 权限认证拦截
        registry.addInterceptor(authInterceptor)
                // 拦截会员中心相关请求接口
                .addPathPatterns(ApiRouterConsts.API_FRONT_USER_URL_PREFIX + "/**",
                        // 拦截作家后台相关请求接口
                        ApiRouterConsts.API_AUTHOR_URL_PREFIX + "/**",
                        // 拦截平台后台相关请求接口
                        ApiRouterConsts.API_ADMIN_URL_PREFIX + "/**")
                // 放行登录注册相关请求接口
                .excludePathPatterns(ApiRouterConsts.API_FRONT_USER_URL_PREFIX + "/register",
                        ApiRouterConsts.API_FRONT_USER_URL_PREFIX + "/login",
                        ApiRouterConsts.API_ADMIN_URL_PREFIX + "/login")
                .order(2);

        // Token 解析拦截器
        registry.addInterceptor(tokenParseInterceptor)
                // 拦截小说内容查询接口，需要解析 token 以判断该用户是否有权阅读该章节（付费章节是否已购买）
                .addPathPatterns(ApiRouterConsts.API_FRONT_BOOK_URL_PREFIX + "/content/*")
                .order(3);

    }
}
