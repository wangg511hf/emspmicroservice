package com.volvo.emspmicroservice.cardservice.domain.converter;

import com.volvo.emspmicroservice.cardservice.api.request.CreatCardRequest;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.domain.enumType.CardStatus;
import org.springframework.stereotype.Component;

@Component
public class CardRequestConverter {

    public Card fromRequest(CreatCardRequest creatCardRequest) {
        return new Card(
                creatCardRequest.getId(),
                creatCardRequest.getCardNum(),
                creatCardRequest.getAccountId(),
                creatCardRequest.getContractId(),
                CardStatus.valueOf(creatCardRequest.getCardStatus()),
                creatCardRequest.getCreateTime(),
                creatCardRequest.getLastUpdated()
        );
    }
}
