package novel.learn.myNovel;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("novel.learn.myNovel.dao.mapper")
@EnableCaching
@Slf4j
@EnableScheduling
public class MyNovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNovelApplication.class, args);
	}

}
