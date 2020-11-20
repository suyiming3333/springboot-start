package com.corn.springboot.start.spring.event.custom;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: EventListener
 * @Package com.corn.springboot.start.spring.event
 * @Description: 事件监听者
 * @date 2020/11/20 10:34
 */
public interface EventListener<E extends AbstractEvent> {

    /**响应事件的监听**/
    void onEvnent(E event);
}
