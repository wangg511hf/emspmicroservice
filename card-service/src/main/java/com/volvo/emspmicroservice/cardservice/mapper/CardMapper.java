package com.volvo.emspmicroservice.cardservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volvo.emspmicroservice.cardservice.domain.Card;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper extends BaseMapper<Card> {
}
