package com.corn.springboot.start.websocket;

import com.corn.springboot.start.websocket.common.MyClusterWebSocketHandler;
import com.corn.springboot.start.websocket.common.MyHasdShakeInterceptor;
import com.corn.springboot.start.websocket.common.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: WebSocketConfig
 * @Package com.corn.sprngboot.websocket.demo.config
 * @Description: TODO
 * @date 2020/9/28 17:03
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MyClusterWebSocketHandler myClusterWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(myWebSocketHandler, "/testWebSocket")//添加接收请求的handler
                .addInterceptors(new MyHasdShakeInterceptor())//添加拦截器(用于处理参数)
                .setAllowedOrigins("http://www.websocket-test.com");//设置origins源

    }
}
