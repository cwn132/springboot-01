package com.cwn.springboot;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.Queue;
import java.util.logging.Logger;

@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"com.*.*"})
@ComponentScan(basePackages = "com.cwn")
@MapperScan("com.cwn.springboot.Mapper")
@EnableCaching
@EnableJms
public class Springboot01Application extends SpringBootServletInitializer {

//
//    private static Logger logger = (Logger) LoggerFactory.getLogger(Springboot01Application.class);

//    @Bean
//    public Queue queue(){
//
//        return (Queue) new ActiveMQQueue("pay.request");
//    }


    public static void main(String[] args) {
        SpringApplication.run(Springboot01Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("外部tomcat,chapter启动!");
        return application.sources(Springboot01Application.class);
    }

}

