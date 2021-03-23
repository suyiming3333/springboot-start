package com.corn.springboot.start;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableWebSecurity
public class StartApplication {

    public static void main(String[] args) {
        /**
         * -Dcsp.sentinel.dashboard.server=localhost:10880 ��������sentinel
         */
        SpringApplication.run(StartApplication.class, args);

//        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);
//
//        // ���ӵ�����̨����sentinel����̨ͨ��
//        System.setProperty("project.name",
//                context.getEnvironment().getProperty("spring.application.name","sentinel"));
//        System.setProperty("csp.sentinel.dashboard.server",
//                context.getEnvironment().getProperty("sentinel.dashboard.server","localhost:10880"));
//        InitExecutor.doInit();

//        try {
//            new NettyServer(12345).run();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
    }

}
