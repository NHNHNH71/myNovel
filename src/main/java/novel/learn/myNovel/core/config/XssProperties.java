package novel.learn.myNovel.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "novel.xss")
public record XssProperties(Boolean enabled, List<String> excludes) {
}
