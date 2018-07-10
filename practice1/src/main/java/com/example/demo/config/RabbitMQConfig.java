package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.order.routingkey}")
    private String ROUTING_KEY;

    @Value("${rabbitmq.order.queue.buy}")
    private String ORDER_BUY_QUEUE;

    @Value("${rabbitmq.order.queue.sell}")
    private String ORDER_SELL_QUEUE;

    @Value("${rabbitmq.order.exchange.buy}")
    private String ORDER_BUY_EXCHANGE;

    @Value("${rabbitmq.order.exchange.sell}")
    private String ORDER_SELL_EXCHANGE;

    @Value("${rabbitmq.host}")
    private String rmqHost;
    @Value("${rabbitmq.user}")
    private String rmqUser;
    @Value("${rabbitmq.password}")
    private String rmqPass;

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setAddresses(rmqHost);
        factory.setUsername(rmqUser);
        factory.setPassword(rmqPass);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setConnectionFactory(connectionFactory());
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
