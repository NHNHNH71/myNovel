package novel.learn.myNovel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("novel.learn.myNovel.dao.mapper")
public class MyNovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyNovelApplication.class, args);
	}

}
