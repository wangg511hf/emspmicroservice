package com.volvo.emspmicroservice.emspapi.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange requestExchange() {
        return new TopicExchange("request.exchange");
    }

    @Bean
    public Queue accoutServiceQueue() {
        return new Queue("account.service.queue", true);
    }

    @Bean
    public Queue cardServiceQueue() {
        return new Queue("card.service.queue", true);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(accoutServiceQueue())
                .to(requestExchange())
                .with("account.service.routing.key");
    }

    @Bean
    public Binding paymentBinding() {
        return BindingBuilder.bind(cardServiceQueue())
                .to(requestExchange())
                .with("card.service.routing.key");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
