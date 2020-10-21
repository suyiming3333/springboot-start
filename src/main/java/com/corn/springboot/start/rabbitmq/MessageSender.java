package com.corn.springboot.start.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MessageSender
 * @Package com.nsg.snt.rabbitmq
 * @Description: TODO
 * @date 2019/8/21 17:21
 */

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) throws Exception{
        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId("1");

        rabbitTemplate.convertAndSend(
                "my-topic-exchange",//交换机
                "my.x",//路由键
                msg,//消息内容
                correlationData//消息唯一ID
        );

    }
}
