package com.corn.springboot.start.spring.event.custom;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: EventMulticaster
 * @Package com.corn.springboot.start.spring.event
 * @Description: TODO
 * @date 2020/11/20 10:37
 */
public interface EventMulticaster {

    /**广播事件**/
    void multicastEvent(AbstractEvent event);

    /**添加事件监听者**/
    void addEventListener(EventListener<?> listener);

    /**删除事件监听者**/
    void removeEventListener(EventListener<?> listener);
}
