package com.cwn.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.*.*"})
@ComponentScan(basePackages = "com.cwn")
@MapperScan("com.cwn.springboot.Mapper")
public class Springboot01Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("外部tomcat,chapter启动!");
        return application.sources(Springboot01Application.class);
    }

}

