package com.volvo.emspmicroservice.accountservice.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volvo.emspmicroservice.accountservice.infrastructure.DO.AccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<AccountDO> {
}
