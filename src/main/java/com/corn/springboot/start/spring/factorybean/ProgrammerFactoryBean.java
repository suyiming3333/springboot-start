package com.corn.springboot.start.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ProgrammerFactoryBean
 * @Package com.corn.springboot.start.spring
 * @Description: 用于创建程序员bean工厂类
 * @date 2020/10/27 11:03
 */
public class ProgrammerFactoryBean implements FactoryBean<Programmer> {

    private ProgrammingSkillService programmingSkillService;
    private String languageType;


    /**
     * 返回bean实例对象
     * @return
     * @throws Exception
     */
    @Override
    public Programmer getObject() throws Exception {
        return new CommonProgrammer(programmingSkillService,languageType);
    }

    /**
     * 返回bean的类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Programmer.class;
    }

    /**
     * 默认单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }

    public ProgrammingSkillService getProgrammingSkillService() {
        return programmingSkillService;
    }

    public void setProgrammingSkillService(ProgrammingSkillService programmingSkillService) {
        this.programmingSkillService = programmingSkillService;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }
}
