package com.volvo.emspmicroservice.cardservice.infrastructure.converter;

import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.infrastructure.DO.CardDO;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public CardDO fromDomain(Card card) {
        return new CardDO(
                card.getId(),
                card.getCardNum(),
                card.getAccountId(),
                card.getContractId(),
                card.getCardStatus(),
                card.getCreateTime(),
                card.getLastUpdated()
        );
    }

    public Card toDomain(CardDO cardDO) {
        return new Card(
                cardDO.getId(),
                cardDO.getCardNum(),
                cardDO.getAccountId(),
                cardDO.getContractId(),
                cardDO.getCardStatus(),
                cardDO.getCreateTime(),
                cardDO.getLastUpdated()
        );
    }
}
