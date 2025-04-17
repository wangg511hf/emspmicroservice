package com.volvo.emspmicroservice.accountservice.service;

import com.rabbitmq.client.Channel;
import com.volvo.emspmicroservice.common.client.AccountClient;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AccountServiceConsumer {

    @Autowired
    private AccountClient accountClient;

    @RabbitListener(queues = "account.service.queue")
    public void processAccount(AccountDTO accountDTO, Message message, Channel channel) throws IOException {
        try {
            Result res = accountClient.createAccount(accountDTO);
            System.out.println("id: " + accountDTO.getId());
            System.out.println("name: " + accountDTO.getName());
            System.out.println("email: " + accountDTO.getEmail());
            System.out.println("username: " + accountDTO.getUsername());
            System.out.println("password: " + accountDTO.getPassword());
            System.out.println("accountStatus: " + accountDTO.getAccountStatus());
            System.out.println("Account created successfully!");

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }
}
