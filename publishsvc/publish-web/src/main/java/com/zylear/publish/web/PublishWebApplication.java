package com.zylear.publish.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration
@ComponentScan("com.zylear.publish.web.*")
@EnableAutoConfiguration(exclude = {
////		DataSourceAutoConfiguration.class,
//		DataSourceTransactionManagerAutoConfiguration.class,
//		MybatisAutoConfiguration.class,
////		DispatcherServletAutoConfiguration.class, /**如果需要自定义servlet dispatch,需要exclude*/
////		ErrorMvcAutoConfiguration.class,
////		RabbitAutoConfiguration.class,
		DataSourceAutoConfiguration.class
})
public class PublishWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublishWebApplication.class, args);
	}
}
