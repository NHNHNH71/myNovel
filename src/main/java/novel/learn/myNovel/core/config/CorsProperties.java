package novel.learn.myNovel.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
/**
 * 配置跨域属性
 * */
@ConfigurationProperties(prefix = "novel.cors")
@Data
public class CorsProperties {
    /**
     * 允许跨域的域名
     * */
    private List<String> allowOrigins;
}
