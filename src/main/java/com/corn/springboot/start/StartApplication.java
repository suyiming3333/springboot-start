package com.corn.springboot.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan("com.corn.springboot.start.**.mapper")
@SpringBootApplication
@EnableWebSecurity
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);

//        try {
//            new NettyServer(12345).run();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
    }

}
