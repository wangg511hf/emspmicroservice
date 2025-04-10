package com.volvo.emspmicroservice.accountservice.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emspmicroservice.accountservice.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.accountservice.domain.Account;
import com.volvo.emspmicroservice.accountservice.mapper.AccountMapper;
import com.volvo.emspmicroservice.common.query.PageQuery;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public boolean isAccountExists(String email) {
        return this.exists(new LambdaQueryWrapper<Account>()
                .eq(Account::getEmail, email));
    }

    @Override
    public PageDTO<AccountDTO> queryAccountPage(PageQuery query) {
        Page<Account> queryRes = lambdaQuery()
                .orderByDesc(Account::getLastUpdated)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));

        PageDTO<AccountDTO> res = new PageDTO<>();
        res.setTotalNum(queryRes.getTotal());
        res.setTotalPage(queryRes.getPages());

        List<Account> records = queryRes.getRecords();
        if(CollUtil.isEmpty(records)) {
            res.setList(Collections.emptyList());
            return res;
        }
        List<AccountDTO> dtos = CollUtil.newArrayList();
        for(Account record : records) {
            dtos.add(new AccountDTO(record));
        }
        res.setList(dtos);

        return res;
    }
}
