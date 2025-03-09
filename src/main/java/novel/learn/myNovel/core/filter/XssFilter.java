package novel.learn.myNovel.core.filter;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.config.XssProperties;
import novel.learn.myNovel.core.wrapper.XssHttpServletRequestWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "novel.xss.enabled", havingValue = "true")
@WebFilter(urlPatterns = "/*", filterName = "xssFilter")
@EnableConfigurationProperties(value = {XssProperties.class})
@RequiredArgsConstructor
public class XssFilter implements Filter {
    private final XssProperties xssProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (handleExcludeUrl(req)) {
            //直接通过，不对内容进行审核
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //继承了HttpServletRequestWrapper类
        //重写了getParameterValues方法，这个方法中对拦截的请求进行具体的检查最终通过doFilter进行过滤
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
                (HttpServletRequest) servletRequest);
        filterChain.doFilter(xssRequest, servletResponse);
    }

    private boolean handleExcludeUrl(HttpServletRequest request) {
        //列表为空，返回false
        if (CollectionUtils.isEmpty(xssProperties.excludes())) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : xssProperties.excludes()) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        jakarta.servlet.Filter.super.destroy();
    }
}
