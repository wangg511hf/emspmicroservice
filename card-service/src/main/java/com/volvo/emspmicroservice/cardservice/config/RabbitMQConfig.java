package com.volvo.emspmicroservice.cardservice.config;

import com.volvo.emspmicroservice.cardservice.listener.RabbitMQMessageListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RabbitMQConfig {

    //@Value("${amqp.rabbitmq.host}")
    private String host = "rabbitmq-server";
    //@Value("${amqp.rabbitmq.port}")
    private Integer port = 5672;
    //@Value("${amqp.rabbitmq.username}")
    private String username = "guest";
    //@Value("${amqp.rabbitmq.password}")
    private String password = "guest";
    //@Value("${amqp.rabbitmq.vhost}")
    private String vhost = "emspvhost";
    //@Value("${amqp.rabbitmq.queue.name}")
    private String queueName = "emsp.consumer.queue";
    //@Value("${amqp.rabbitmq.exchange.name}")
    private String exchangeName = "emsp.exchange.fanout";
    //@Value("${amqp.rabbitmq.routing.key}")
    private String routingKey = "emsp.message.key";

    @Bean
    public com.rabbitmq.client.ConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        factory.setHost(this.host);
        factory.setPort(this.port);
        factory.setUsername(this.username);
        factory.setPassword(this.password);
        factory.setVirtualHost(this.vhost);

        return factory;
    }

    @Bean
    public org.springframework.amqp.rabbit.connection.ConnectionFactory springConnectionFactory(
                                                                com.rabbitmq.client.ConnectionFactory connectionFactory) {
        org.springframework.amqp.rabbit.connection.ConnectionFactory springFactory =
                new org.springframework.amqp.rabbit.connection.PooledChannelConnectionFactory(connectionFactory);

        return springFactory;
    }

    @Bean
    public org.springframework.amqp.core.Queue queue() {
        return new org.springframework.amqp.core.Queue(this.queueName, true, false, true);
    }

    @Bean
    public RabbitMQMessageListener rabbitMQMessageListener() {
        return new RabbitMQMessageListener();
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(RabbitMQMessageListener rabbitMQMessageListener,
                                                            org.springframework.amqp.core.Queue queue,
                                                            org.springframework.amqp.rabbit.connection.ConnectionFactory factory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
        container.setConcurrentConsumers(5);
        container.setMaxConcurrentConsumers(10);
        container.setMessageListener(rabbitMQMessageListener);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.addQueues(queue);
        container.initialize();

        return container;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
        policy.setInitialInterval(500);
        policy.setMaxInterval(10000);
        policy.setMultiplier(10.0);
        retryTemplate.setBackOffPolicy(policy);

        return retryTemplate;
    }

    @Bean
    public Exchange exchange() {
        return new FanoutExchange(this.exchangeName);
    }

    @Bean
    public Binding binding(Exchange exchange, org.springframework.amqp.core.Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(this.routingKey).noargs();
    }

    @Bean
    public RabbitAdmin admin(RetryTemplate retryTemplate,
                             Binding binding,
                             Exchange exchange,
                             org.springframework.amqp.core.Queue queue,
                             org.springframework.amqp.rabbit.connection.ConnectionFactory factory) {
        RabbitAdmin admin = new RabbitAdmin(factory);
        admin.setRetryTemplate(retryTemplate);
        admin.declareQueue(queue);
        admin.declareExchange(exchange);
        admin.declareBinding(binding);

        return admin;
    }
}
