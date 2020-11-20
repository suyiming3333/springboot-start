package com.corn.springboot.start.spring.event.custom.impl;

import com.corn.springboot.start.spring.event.custom.AbstractEvent;
import com.corn.springboot.start.spring.event.custom.EventListener;
import com.corn.springboot.start.spring.event.custom.EventMulticaster;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CommonEventMulticaster
 * @Package com.corn.springboot.start.spring.event
 * @Description: TODO
 * @date 2020/11/20 10:49
 */
public class CommonEventMulticaster implements EventMulticaster {

    /**存放事件、事件监听者的列表**/
    private Map<Class<?>, List<EventListener>> eventObjectEventListenerMap = new ConcurrentHashMap<>();



    @Override
    public void multicastEvent(AbstractEvent event) {
        List<EventListener> listeners = eventObjectEventListenerMap.get(event.getClass());
        if(listeners!=null){
            listeners.stream().forEach(eventListener -> {eventListener.onEvnent(event);});
        }
    }

    @Override
    public void addEventListener(EventListener<?> listener) {
        Class<?> eventType = this.getEventType(listener);
        List<EventListener> eventListeners = this.eventObjectEventListenerMap.get(eventType);
        if (eventListeners == null) {
            eventListeners = new ArrayList<>();
            this.eventObjectEventListenerMap.put(eventType, eventListeners);
        }
        eventListeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener<?> listener) {
        Class<?> eventType = this.getEventType(listener);
        List<EventListener> eventListeners = this.eventObjectEventListenerMap.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }


    /**
     * 获取事件监听器监听的事件类型
     * 即：EventListener<E extends AbstractEvent> 的E
     * @param listener
     * @return
     */
    protected Class<?> getEventType(EventListener listener) {
        ParameterizedType parameterizedType = (ParameterizedType) listener.getClass().getGenericInterfaces()[0];
        Type eventType = parameterizedType.getActualTypeArguments()[0];
        return (Class<?>) eventType;
    }

}
