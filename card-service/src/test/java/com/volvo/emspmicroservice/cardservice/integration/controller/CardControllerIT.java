package com.volvo.emspmicroservice.cardservice.integration.controller;

import com.volvo.emspmicroservice.cardservice.api.request.AssignCardRequest;
import com.volvo.emspmicroservice.cardservice.api.request.ChangeCardStatusRequest;
import com.volvo.emspmicroservice.cardservice.api.request.CreatCardRequest;
import com.volvo.emspmicroservice.cardservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.api.controller.CardController;
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
        CreatCardRequest ccr = new CreatCardRequest();
        ccr.setCardNum("8564745522221111");
        ccr.setCardStatus("CREATED");

        ApiResponse<Card> res = cardController.createCard(ccr);
        assertEquals(200, res.getCode());
    }

    @Test
    void assign() {
        AssignCardRequest acr = new AssignCardRequest();
        acr.setId(4);
        acr.setAccountId(8);

        ApiResponse<Card> res = cardController.assign(acr);
        assertEquals(200, res.getCode());
    }

    @Test
    void changeCardStatus() {
        ChangeCardStatusRequest ccsr = new ChangeCardStatusRequest();
        ccsr.setId(6);
        ccsr.setCardStatus("DEACTIVATED");

        ApiResponse<Card> res = cardController.changeCardStatus(ccsr);
        assertEquals(200, res.getCode());
    }

    @Test
    void queryCardPage() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(2);

        PageDTO<Card> testRes = cardController.queryCardPage(query);
        assertEquals(4L, testRes.getTotalNum());
        assertEquals(2L, testRes.getTotalPage());
    }
}