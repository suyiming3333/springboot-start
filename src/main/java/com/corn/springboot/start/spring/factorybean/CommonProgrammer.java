package com.corn.springboot.start.spring.factorybean;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CommonProgrammer
 * @Package com.corn.springboot.start.spring
 * @Description: TODO
 * @date 2020/10/27 10:54
 */
public class CommonProgrammer implements Programmer{

    /**
     * 编程技能
     */
    private ProgrammingSkillService programmingSkillService;


    /**
     * 编程语言
     */
    private String languageType;

    public CommonProgrammer(ProgrammingSkillService programmingSkillService, String languageType) {
        this.programmingSkillService = programmingSkillService;
        this.languageType = languageType;
    }

    @Override
    public void eat() {

    }

    @Override
    public void coding() {
        programmingSkillService.codingSkill();
    }

    @Override
    public void sleep() {

    }
}
