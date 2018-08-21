package com.zylear.publish.web;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableAutoConfiguration

@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		MybatisAutoConfiguration.class
////		DispatcherServletAutoConfiguration.class, /**如果需要自定义servlet dispatch,需要exclude*/
////		ErrorMvcAutoConfiguration.class,
////		RabbitAutoConfiguration.class,
//        DataSourceAutoConfiguration.class
})
@ComponentScan("com.zylear.publish.web.*")
public class PublishWebApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(PublishWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PublishWebApplication.class);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setContextPath("/publish");
        configurableEmbeddedServletContainer.setPort(80);
    }
}
