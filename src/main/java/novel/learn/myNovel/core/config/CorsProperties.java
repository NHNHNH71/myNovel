package novel.learn.myNovel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * 配置跨域属性
 * */
import java.util.List;
@ConfigurationProperties(prefix = "novel.cors")
public record CorsProperties(List<String> allowOrigins) {
}
