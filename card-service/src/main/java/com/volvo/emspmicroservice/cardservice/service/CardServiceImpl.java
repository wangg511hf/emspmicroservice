package com.volvo.emspmicroservice.cardservice.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emspmicroservice.cardservice.dto.CardDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.cardservice.domain.Card;
import com.volvo.emspmicroservice.cardservice.mapper.CardMapper;
import com.volvo.emspmicroservice.common.query.PageQuery;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

    @Override
    public PageDTO<CardDTO> queryCardPage(PageQuery query) {
        Page<Card> queryRes = lambdaQuery()
                .orderByDesc(Card::getLastUpdated)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));

        PageDTO<CardDTO> res = new PageDTO<>();
        res.setTotalNum(queryRes.getTotal());
        res.setTotalPage(queryRes.getPages());

        List<Card> records = queryRes.getRecords();
        if(CollUtil.isEmpty(records)){
            res.setList(Collections.emptyList());
            return res;
        }
        List<CardDTO> dtos = CollUtil.newArrayList();
        for(Card record : records) {
            dtos.add(new CardDTO(record));
        }
        res.setList(dtos);

        return res;
    }
}
