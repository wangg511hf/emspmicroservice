package com.volvo.emspmicroservice.cardservice.infrastructure.repository.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emspmicroservice.cardservice.domain.entity.Card;
import com.volvo.emspmicroservice.cardservice.infrastructure.converter.CardConverter;
import com.volvo.emspmicroservice.cardservice.infrastructure.repository.CardRepository;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.infrastructure.DO.CardDO;
import com.volvo.emspmicroservice.cardservice.infrastructure.mapper.CardMapper;
import com.volvo.emspmicroservice.common.query.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardRepositoryImpl extends ServiceImpl<CardMapper, CardDO> implements CardRepository {

    private final CardConverter cardConverter;
    private final CardMapper cardMapper;

    @Override
    public Card createCard(Card card) {
        CardDO cardDO = cardConverter.fromDomain(card);
        boolean isSuccess = this.save(cardDO);
        if(isSuccess) {
            List<CardDO> after = lambdaQuery()
                    .eq(CardDO::getCardNum, cardDO.getCardNum())
                    .eq(CardDO::getContractId, cardDO.getContractId())
                    .eq(CardDO::getCardStatus, cardDO.getCardStatus())
                    .list();
            if(CollUtil.isNotEmpty(after) && after.size() == 1) {
                return cardConverter.toDomain(after.get(0));
            }
        }

        throw new RuntimeException("Card created failed at CardRepository layer!");
    }

    @Override
    public Card getCardById(Integer id) {
        CardDO cardDO = this.getById(id);
        if(cardDO != null) {
            Card card = cardConverter.toDomain(cardDO);
            return card;
        } else {
            throw new RuntimeException("Card does not exist!");
        }
    }

//    @Override
//    public Card assignToAccount(Integer id, Integer accountId) {
//        CardDO cardDO = this.getById(id);
//        Card card = cardConverter.toDomain(cardDO);
//        card.setAccountId(accountId);
//        card.assign();
//        boolean isSuccess = this.save(cardConverter.fromDomain(card));
//        if(isSuccess) {
//            return card;
//        }
//
//        throw new RuntimeException("Card assigned failed at CardRepository layer!");
//    }

    @Override
    public PageDTO<Card> queryCardPage(PageQuery query) {
        Page<CardDO> queryRes = lambdaQuery()
                .orderByDesc(CardDO::getLastUpdated)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));

        PageDTO<Card> res = new PageDTO<>();
        res.setTotalNum(queryRes.getTotal());
        res.setTotalPage(queryRes.getPages());

        List<CardDO> records = queryRes.getRecords();
        if (CollUtil.isEmpty(records)) {
            res.setList(Collections.emptyList());
            return res;
        }
        List<Card> list = CollUtil.newArrayList();
        for(CardDO record : records) {
            Card card = new Card(
                    record.getId(),
                    record.getCardNum(),
                    record.getAccountId(),
                    record.getContractId(),
                    record.getCardStatus(),
                    record.getCreateTime(),
                    record.getLastUpdated()
            );

            list.add(card);
        }
        res.setList(list);

        return res;
    }
}
