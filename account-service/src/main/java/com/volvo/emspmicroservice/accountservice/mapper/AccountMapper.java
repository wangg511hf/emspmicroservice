package com.volvo.emspmicroservice.accountservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volvo.emspmicroservice.accountservice.domain.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
