package lhonl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//	开启缓存
@EnableCaching
@MapperScan("lhonl.dao")
@SpringBootApplication
public class Project02Application {

	public static void main(String[] args) {
		SpringApplication.run(Project02Application.class, args);
	}

}
