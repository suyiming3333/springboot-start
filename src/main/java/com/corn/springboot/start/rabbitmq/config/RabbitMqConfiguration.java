package com.corn.springboot.start.rabbitmq.config;

import com.corn.springboot.start.rabbitmq.listener.QueueAListener;
import com.corn.springboot.start.rabbitmq.listener.QueueBListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: RabbitMqConfiguration
 * @Package com.corn.springboot.start.rabbitmq
 * @Description: TODO
 * @date 2020/10/13 10:32
 */


@Slf4j
@Configuration
public class RabbitMqConfiguration {

    @Autowired
    private QueueAListener queueAListener;

    @Autowired
    private QueueBListener queueBListener;

    @Autowired
    private Environment env;

    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;




//    @Bean
//    public CachingConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("139.9.0.236");
//        connectionFactory.setPort(5673);
//        connectionFactory.setUsername("corn");
//        connectionFactory.setPassword("corn#9527");
//        connectionFactory.setVirtualHost("dev");
//        return connectionFactory;
//    }

    @Bean
    public TopicExchange myTopicExchange(){
        return new TopicExchange("my-topic-exchange",true,false);
    }

    @Bean
    public FanoutExchange myFanoutExchange(){
        return new FanoutExchange("my-fanout-exchange",true,false);
    }

    @Bean
    public Queue myQueueA(){

        //声明重试队列,重试队列比较特殊，需要设置两个参数
        Map<String,Object> arg = new HashMap<String,Object>();
        //参数1：将消息发送到哪一个转发器
        arg.put("x-dead-letter-exchange","my-topic-exchange");

        return new Queue("myQueueA",true,false,false,arg);
    }

    @Bean
    public Queue myQueueB(){
        return new Queue("myQueueB",true);
    }


    @Bean
    public Binding aBinding(){
        return BindingBuilder.bind(myQueueA()).to(myTopicExchange()).with("my.x");
    }

    @Bean
    public Binding bBinding(){
        return BindingBuilder.bind(myQueueB()).to(myTopicExchange()).with("my.x");
    }

    @Bean
    public Binding aaBinding(){
        return BindingBuilder.bind(myQueueA()).to(myFanoutExchange());
    }

    @Bean
    public Binding bbBinding(){
        return BindingBuilder.bind(myQueueB()).to(myFanoutExchange());
    }


    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
        /**开启发布确认模式**/
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        /**开启消息发送失败返回(投递不到对应队列时处理)**/
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /**return方法将消息返还给生产者时，当mandatory设置为false时，出现上述情况broker会直接将消息丢弃**/
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //发送成功的回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
            }
        });
        //发送失败return时的回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

    /**
     * 单消费者监听容器工厂(主要用于配置listener)
     * @return
     */
    @Bean("singleListenerContainer")
    public SimpleRabbitListenerContainerFactory singleListenerContainer(CachingConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
//        factory.setTxSize(1);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//自动签收消息
        return factory;
    }


    /**
     * 多消费者监听容器工厂(主要用于配置listener)
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(CachingConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }


    /**
     * 配置基于手动确认机制的多线程并发listener
     * 给某个队列的监听容器配置监听容器
     * @param myQueueA
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer featureMessageListenerContainer(@Qualifier("myQueueA") Queue myQueueA,CachingConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        //并发设置
        container.setConcurrentConsumers(10);
        container.setMaxConcurrentConsumers(10);
        container.setPrefetchCount(1);
        container.setMessagePropertiesConverter(new DefaultMessagePropertiesConverter());
        container.setQueues(myQueueA);
        container.setMessageListener(queueAListener);
        //手动确认机制
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

}
