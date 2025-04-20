package com.volvo.emspmicroservice.cardservice.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.infrastructure.DO.CardDO;
import com.volvo.emspmicroservice.common.query.PageQuery;

public interface CardRepository extends IService<CardDO> {

    public Card createCard(Card card);

    public Card getCardById(Integer id);

    public PageDTO<Card> queryCardPage(PageQuery query);
}
