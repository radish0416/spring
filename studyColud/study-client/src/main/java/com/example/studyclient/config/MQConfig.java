package com.example.studyclient.config;

import com.example.studyapi.util.RabbitKey;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import javax.annotation.PostConstruct;

/**
 * rabbit mq config
 * @author Liu Jinglei
 * @Date 2019/6/14
 * @version 1.0
 */
@Configuration
public class MQConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Bean
    public Queue appQueue() {
        return new Queue(RabbitKey.APP_QUEUE, true);
    }

    @Bean
    public Queue appPushMessageAndroidOrderingQueue() {
        return new Queue(RabbitKey.APP_PUSH_ANDROID_MESSAGE_ORDERING_QUEUE, true);
    }
    @Bean
    public Queue appPushMessageIOSOrderingQueue() {
        return new Queue(RabbitKey.APP_PUSH_IOS_MESSAGE_ORDERING_QUEUE, true);
    }
    @Bean
    public Queue appImageUploadCompression(){return new Queue(RabbitKey.IMAGE_UPLOAD_COMPRESSION,true);}

    @Bean
    public Queue sendAwsSms() {
        return new Queue(RabbitKey.SEND_AWS_SMS, true);
    }

    @Bean
    public Queue acttivaUserBySMS() {
        return new Queue(RabbitKey.SEND_SMS_DOWNLOAD, true);
    }

    @Bean
    public Queue walletPushQueueAndroid() {
        return new Queue(RabbitKey.WALLET_PUSH_ANDROID, true);
    }
    @Bean
    public Queue walletPushQueueIOS() {
        return new Queue(RabbitKey.WALLET_PUSH_IOS, true);
    }


    @PostConstruct
    //必须是prototype类型
    public RabbitTemplate initRabbitTemplate() {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}

@Configuration
class RabbitConfig implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(new MappingJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

}
