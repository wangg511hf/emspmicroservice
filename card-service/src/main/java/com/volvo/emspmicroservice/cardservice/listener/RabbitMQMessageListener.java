package com.volvo.emspmicroservice.cardservice.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("RabbitMQMessageListener received a message: " + new String(message.getBody()));
    }
}
