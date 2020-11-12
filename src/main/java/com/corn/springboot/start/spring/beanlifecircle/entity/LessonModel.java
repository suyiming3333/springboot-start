package com.corn.springboot.start.spring.beanlifecircle.entity;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.corn.springboot.start.spring.beanlifecircle.entity
 * @Description: TODO
 * @date 2020/11/12 12:34
 */
public class LessonModel {
    //课程名称
    private String name;
    //课时
    private int lessonCount;
    //描述信息
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LessonModel{" +
                "name='" + name + '\'' +
                ", lessonCount=" + lessonCount +
                ", description='" + description + '\'' +
                '}';
    }
}
