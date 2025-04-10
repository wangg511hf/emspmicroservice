package com.volvo.emspmicroservice.cardservice.unit.controller;

import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.client.AccountClient;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.util.CommonUtil;
import com.volvo.emspmicroservice.cardservice.controller.CardController;
import com.volvo.emspmicroservice.cardservice.domain.Card;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.common.query.PageQuery;
import com.volvo.emspmicroservice.cardservice.service.CardService;
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
    private CardService cardService;

    @Mock
    private CommonUtil commonUtil;

    @Mock
    private AccountClient accountClient;

    @Test
    public void createCard_SuccessWhenInputValid() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(1);
        cardDTO.setCardNum("1234567891011121");
        cardDTO.setAccountId(1);
        cardDTO.setContractId(commonUtil.generateRandomEMAID());
        cardDTO.setCardStatus("CREATED");

        when(cardService.save(any(Card.class))).thenReturn(true);

        Result testRes = cardController.createCard(cardDTO);

        assertEquals(Result.of(200, "Card created successfully!"), testRes);

        verify(cardService).save(any(Card.class));
    }

    @Test
    public void createCard_SuccessWhenCardNumNotValid() {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(1);
        cardDTO.setCardNum("123456789101112131415"); // Card num not 16
        cardDTO.setAccountId(1);
        cardDTO.setContractId(commonUtil.generateRandomEMAID());
        cardDTO.setCardStatus("CREATED");

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> cardController.createCard(cardDTO)
        );

        assertEquals("Card number must be 16 characters!", exception.getMessage());
        verify(cardService, never()).save(any());
    }

    @Test
    public void assign_SuccessWhenCardIsValid() {
        int id = 1;
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(id);
        cardDTO.setCardNum("1234567891011121");
        cardDTO.setContractId(commonUtil.generateRandomEMAID());
        cardDTO.setCardStatus("CREATED");

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(id);
        accountDTO.setAccountStatus("CREATED");

        when(cardService.getById(id)).thenReturn(new Card(cardDTO));
        when(accountClient.getAccountById(id)).thenReturn(accountDTO);

        Result testRes = cardController.assign(id);

        assertEquals(Result.of(200, "Card assigned successfully!"), testRes);
        verify(cardService).updateById(any(Card.class));
    }

    @Test
    public void assign_FailedWhenCardNotExists() {
        int id = 1;
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(id);
        cardDTO.setCardNum("1234567891011121");
        cardDTO.setAccountId(1);
        cardDTO.setContractId(commonUtil.generateRandomEMAID());
        cardDTO.setCardStatus("CREATED");

        when(cardService.getById(id)).thenReturn(null);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> cardController.assign(id)
        );

        assertEquals("Card not found with id: " + id, exception.getMessage());
        verify(cardService, never()).updateById(any());
    }

    @Test
    public void queryCardPage_SuccessWhenWithNoData() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(10);

        PageDTO<CardDTO> mockPageData = new PageDTO<>();
        mockPageData.setTotalPage(0L);
        mockPageData.setTotalNum(0L);
        mockPageData.setList(Collections.emptyList());

        when(cardService.queryCardPage(query)).thenReturn(mockPageData);

        PageDTO<CardDTO> testRes = cardController.queryCardPage(query);

        assertEquals(0L, testRes.getTotalNum());
        assertEquals(0L, testRes.getTotalPage());
        assertEquals(Collections.emptyList(), testRes.getList());
    }
}
