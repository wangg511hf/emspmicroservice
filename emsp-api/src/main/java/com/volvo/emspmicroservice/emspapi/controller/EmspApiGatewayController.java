package com.volvo.emspmicroservice.emspapi.controller;

import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmspApiGatewayController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/account/create")
    public Result createAccount(@RequestBody AccountDTO accountDTO) {
        rabbitTemplate.convertAndSend("request.exchange", "account.service.routing.key", accountDTO);

        return Result.of(200, "createAccount API send successfully!");
    }

    @PostMapping("/card/create")
    public Result createCard(@RequestBody CardDTO cardDTO) {
        rabbitTemplate.convertAndSend("request.exchange", "card.service.routing.key", cardDTO);

        return Result.of(200, "createCard API send successfully!");
    }
}
