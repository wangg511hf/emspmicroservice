package com.volvo.emspmicroservice.cardservice.integration.controller;

import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.controller.CardController;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.common.query.PageQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardControllerIT {

    @Autowired
    private CardController cardController;

    @Test
    void createCard() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setCardNum("8564745522221111");
        //cardDTO.setAccount_id(5);
        cardDTO.setCardStatus("CREATED");

        Result res = cardController.createCard(cardDTO);
        assertEquals(Result.of(200, "Card created successfully!"), res);
    }

    @Test
    void assign() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(4);
        cardDTO.setAccountId(8);
        cardDTO.setCardNum("1233054617560236");
        cardDTO.setCardStatus("ASSIGNED");

        Result res = cardController.assign(1, cardDTO);
        assertEquals(Result.of(200, "Card assigned successfully!"), res);
    }

    @Test
    void changeCardStatus() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(6);
        cardDTO.setCardNum("1233054617560236");
        cardDTO.setCardStatus("DEACTIVATED");

        Result res = cardController.changeCardStatus(cardDTO.getId(), cardDTO);
        assertEquals(Result.of(200, "Card status changed successfully!"), res);
    }

    @Test
    void queryCardPage() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(2);

        PageDTO<CardDTO> testRes = cardController.queryCardPage(query);
        assertEquals(4L, testRes.getTotalNum());
        assertEquals(2L, testRes.getTotalPage());
    }
}