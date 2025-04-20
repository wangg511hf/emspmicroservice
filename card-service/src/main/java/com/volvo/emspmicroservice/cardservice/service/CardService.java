package com.volvo.emspmicroservice.cardservice.service;

import com.volvo.emspmicroservice.cardservice.api.request.AssignCardRequest;
import com.volvo.emspmicroservice.cardservice.api.request.ChangeCardStatusRequest;
import com.volvo.emspmicroservice.cardservice.api.request.CreatCardRequest;
import com.volvo.emspmicroservice.cardservice.domain.converter.CardRequestConverter;
import com.volvo.emspmicroservice.cardservice.domain.entity.Account;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.infrastructure.converter.CardConverter;
import com.volvo.emspmicroservice.cardservice.infrastructure.repository.CardRepository;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CardService {

    private final FeignService feignService;
    private final CardRepository cardRepository;
    private final CardRequestConverter cardRequestConverter;
    private final CardConverter cardConverter;
    @Value("${custom.id-config.country-codes}")
    private String[] countryCodes;
    @Value("${custom.id-config.vendor-codes}")
    private String[] vendorCodes;

    public Card createCard(CreatCardRequest creatCardRequest) {
        if(creatCardRequest.getCardNum().length() != 16) {
            throw new RuntimeException("Card number must be 16 characters!");
        }

        Card before = cardRequestConverter.fromRequest(creatCardRequest);
        //Generate random EMAID format contractId
        String COUNTRY_CODE = countryCodes[ThreadLocalRandom.current().nextInt(countryCodes.length)];
        String VENDOR_CODE = vendorCodes[ThreadLocalRandom.current().nextInt(vendorCodes.length)];
        before.generateEMAID(COUNTRY_CODE, VENDOR_CODE);
        if(before.getId() == null) {
            Card after = cardRepository.createCard(before);
            return after;
        }

        throw new RuntimeException("Card created failed at CardService layer!");
    }

    public Card assign(AssignCardRequest acr) {
        Card card = cardRepository.getCardById(acr.getId());

        // Only for testing feign service function
        AccountDTO accountDTO = feignService.getAccountById(acr.getAccountId());
        if(accountDTO != null) {
            card.setAccountId(accountDTO.getId());
            card.assign();
        } else {
            throw new RuntimeException("Card assigned failed at CardService layer due to account feign service error!");
        }

        boolean isSuccess = cardRepository.updateById(cardConverter.fromDomain(card));
        if(isSuccess) {
            return card;
        }

        throw new RuntimeException("Card assign failed at CardService layer!");
    }

    public Card changeCardStatus(ChangeCardStatusRequest acr) {
        Card card = cardRepository.getCardById(acr.getId());

        if(acr.getCardStatus().equals("CREATED")) {
            card.create();
        } else if(acr.getCardStatus().equals("ASSIGNED")) {
            card.assign();
        } else if(acr.getCardStatus().equals("ACTIVATED")) {
            card.activate();
        } else if(acr.getCardStatus().equals("DEACTIVATED")) {
            card.deactivate();
        } else {
            throw new RuntimeException("Wrong Account status input!");
        }

        boolean isSuccess = cardRepository.updateById(cardConverter.fromDomain(card));
        if(isSuccess) {
            return card;
        }

        throw new RuntimeException("Card assign failed at CardService layer!");
    }

    public PageDTO<Card> queryCardPage(PageQuery query) {
        if(query.getPageNum() == null || query.getPageSize() == null) {
            throw new RuntimeException("Page number or page size is missing!");
        }

        PageDTO<Card> cards = cardRepository.queryCardPage(query);
        return cards;
    }
}
