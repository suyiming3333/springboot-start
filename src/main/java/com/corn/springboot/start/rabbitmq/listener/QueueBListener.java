package com.corn.springboot.start.rabbitmq.listener;


import cn.hutool.json.JSONUtil;
import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: RegisterFeatureListener
 * @Package com.nsg.snt.rabbitmq
 * @Description: 监听提取特征码消息队列：sendFeatureFlag，然后注册人脸特征
 * @date 2019/8/21 21:02
 */

@Slf4j
@Component
public class QueueBListener{


    @RabbitListener(queues = "myQueueB",containerFactory = "singleListenerContainer")
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        log.info("当前处理消息的线程是"+Thread.currentThread().getName());

        try {
            byte[] msg = message.getBody();
            String messageTxt = new String(msg,"UTF-8");

            log.info("收到的消息{}",messageTxt);
            User user = JSONUtil.toBean(messageTxt, User.class);
            System.out.println("user:"+user.getName());
            

            //手动确认接收
            channel.basicAck(deliveryTag,true);
            //不确认签收并重新入队列
//            channel.basicNack(deliveryTag,false,true);
            //不确认签收，不重新入队列
//            channel.basicNack(deliveryTag,false,false);
            //拒绝签收，丢弃该条信息
//            channel.basicReject(deliveryTag,false);
        } catch (Exception e) {
            //不确认签收,重新入队列
            channel.basicNack(deliveryTag,false,true);
            log.error("消息签收失败");
            e.printStackTrace();
        }
    }
}
