package com.corn.springboot.start.spring.factorybean;

import com.corn.springboot.start.spring.factorybean.ProgrammerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SpringConfig
 * @Package com.corn.springboot.start.spring
 * @Description: TODO
 * @date 2020/10/27 11:13
 */
@Configuration
public class SpringConfig {


    /****
     * 通过factorybean 构建bean实例
     * @return
     */
    @Bean
    public ProgrammerFactoryBean aProgrammer(){
        ProgrammerFactoryBean programmerFactoryBean = new ProgrammerFactoryBean();
        programmerFactoryBean.setLanguageType("JAVA");
        programmerFactoryBean.setProgrammingSkillService(()-> System.out.println("java 高并发编程"));
        return programmerFactoryBean;
    }

    @Bean
    public ProgrammerFactoryBean bProgrammer(){
        ProgrammerFactoryBean programmerFactoryBean = new ProgrammerFactoryBean();
        programmerFactoryBean.setLanguageType("C#");
        programmerFactoryBean.setProgrammingSkillService(()-> System.out.println("C# WEB开发"));
        return programmerFactoryBean;
    }

}
