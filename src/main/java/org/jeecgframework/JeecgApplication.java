package org.jeecgframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource(value = {"classpath:spring-*.xml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class, FreeMarkerAutoConfiguration.class, GsonAutoConfiguration.class})
public class JeecgApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeecgApplication.class, args);
	}

}
