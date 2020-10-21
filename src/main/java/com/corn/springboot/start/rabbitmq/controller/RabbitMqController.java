package com.corn.springboot.start.rabbitmq.controller;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.rabbitmq.MessageSender;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: RabbitMqController
 * @Package com.corn.springboot.start.rabbitmq.controller
 * @Description: TODO
 * @date 2020/10/13 11:34
 */

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public String testSend(){
        try {
            messageSender.send("test hhhh");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @GetMapping("/test2")
    public String testSend2(){
        try {
            User user = new User();
            user.setId(10086L);
            user.setName("suyiming");
            user.setAge(20);
            user.setEmail("gmail.com");
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId(user.getId().toString());
            rabbitTemplate.convertAndSend(
                    "my-topic-exchange",//交换机
                    "my.x",//路由键
                    user,//消息内容
                    correlationData//消息唯一ID
            );        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
