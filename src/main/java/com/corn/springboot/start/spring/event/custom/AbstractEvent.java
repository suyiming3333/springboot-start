package com.corn.springboot.start.spring.event.custom;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: AbstractEvent
 * @Package com.corn.springboot.start.spring.event
 * @Description: 抽象事件类
 * @date 2020/11/20 10:33
 */
public abstract class AbstractEvent {

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public AbstractEvent(Object object) {
        this.object = object;
    }

    /**事件源**/
    protected Object object;
}
