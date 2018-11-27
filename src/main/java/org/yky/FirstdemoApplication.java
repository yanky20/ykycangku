package org.yky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.yky.controller.Test2;

@RestController
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@MapperScan(basePackages = "dd.springboot.demo.dao")
public class FirstdemoApplication {

//	@Bean
//	@ConfigurationProperties(prefix = "yky1")
//	public Test2 connectionSettings(){
//		return new Test2();
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(FirstdemoApplication.class, args);
	}

}
