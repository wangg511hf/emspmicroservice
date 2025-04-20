package com.volvo.emspmicroservice.cardservice.unit.controller;

import com.volvo.emspmicroservice.cardservice.api.request.AssignCardRequest;
import com.volvo.emspmicroservice.cardservice.api.request.CreatCardRequest;
import com.volvo.emspmicroservice.cardservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.cardservice.domain.entity.Account;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.domain.enumType.AccountStatus;
import com.volvo.emspmicroservice.cardservice.domain.enumType.CardStatus;
import com.volvo.emspmicroservice.cardservice.infrastructure.converter.CardConverter;
import com.volvo.emspmicroservice.cardservice.service.client.AccountClient;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.api.controller.CardController;
import com.volvo.emspmicroservice.cardservice.infrastructure.DO.CardDO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import com.volvo.emspmicroservice.cardservice.infrastructure.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private AccountClient accountClient;

    @Mock
    private CardConverter cardConverter;

    @Test
    public void createCard_SuccessWhenInputValid() {
        CreatCardRequest ccr = new CreatCardRequest();
        ccr.setId(1);
        ccr.setCardNum("1234567891011121");
        ccr.setCardStatus("CREATED");

        when(cardRepository.save(any(CardDO.class))).thenReturn(true);

        ApiResponse<Card> testRes = cardController.createCard(ccr);

        assertEquals(200, testRes.getCode());

        verify(cardRepository).save(any(CardDO.class));
    }

    @Test
    public void createCard_SuccessWhenCardNumNotValid() {
        CreatCardRequest ccr = new CreatCardRequest();
        ccr.setId(1);
        ccr.setCardNum("123456789101112131415"); // Card num not 16
        ccr.setAccountId(1);
        ccr.setCardStatus("CREATED");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> cardController.createCard(ccr)
        );

        assertEquals("Card number must be 16 characters!", exception.getMessage());
        verify(cardRepository, never()).save(any());
    }

    @Test
    public void assign_SuccessWhenCardIsValid() {
        AssignCardRequest acr = new AssignCardRequest();
        acr.setId(1);
        acr.setAccountId(1);

        CardDO cardDO = new CardDO(1, "1234567890111213", null,
                "CN-CD-1234567890", CardStatus.CREATED, null, null);
        when(cardRepository.getById(1)).thenReturn(cardDO);

        AccountDTO accountDTO = new AccountDTO(1, "adjaad@163.com", "Test",
                "User123", "User123456789", "ACTIVATED", null, null);
        when(accountClient.getAccountById(1)).thenReturn(accountDTO);

        ApiResponse<Card> testRes = cardController.assign(acr);

        assertEquals(200, testRes.getCode());
        verify(cardRepository).updateById(any(CardDO.class));
    }

    @Test
    public void assign_FailedWhenCardNotExists() {
        AssignCardRequest acr = new AssignCardRequest();
        acr.setId(1);
        acr.setAccountId(1);

        when(cardRepository.getById(1)).thenReturn(null);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> cardController.assign(acr)
        );

        assertEquals("Card not found with id: " + 1, exception.getMessage());
        verify(cardRepository, never()).updateById(any());
    }

    @Test
    public void queryCardPage_SuccessWhenWithNoData() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(10);

        PageDTO<Card> mockPageData = new PageDTO<>();
        mockPageData.setTotalPage(0L);
        mockPageData.setTotalNum(0L);
        mockPageData.setList(Collections.emptyList());

        when(cardRepository.queryCardPage(query)).thenReturn(mockPageData);

        PageDTO<Card> testRes = cardController.queryCardPage(query);

        assertEquals(0L, testRes.getTotalNum());
        assertEquals(0L, testRes.getTotalPage());
        assertEquals(Collections.emptyList(), testRes.getList());
    }
}
