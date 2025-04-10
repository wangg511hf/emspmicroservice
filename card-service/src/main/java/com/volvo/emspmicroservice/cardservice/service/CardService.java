package com.volvo.emspmicroservice.cardservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.domain.Card;
import com.volvo.emspmicroservice.common.query.PageQuery;

public interface CardService extends IService<Card> {

    public PageDTO<CardDTO> queryCardPage(PageQuery query);
}
