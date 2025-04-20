package com.volvo.emspmicroservice.cardservice.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volvo.emspmicroservice.cardservice.infrastructure.DO.CardDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper extends BaseMapper<CardDO> {
}
