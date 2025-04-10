package com.volvo.emspmicroservice.accountservice.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.accountservice.domain.Account;
import com.volvo.emspmicroservice.accountservice.mapper.AccountMapper;
import com.volvo.emspmicroservice.common.query.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
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
            AccountDTO dto = new AccountDTO();
            dto.setId(record.getId());
            dto.setEmail(record.getEmail());
            dto.setName(record.getName());
            dto.setUsername(record.getUsername());
            dto.setPassword(record.getPassword());
            dto.setAccountStatus(record.getAccountStatus().name());
            dto.setCreateTime(record.getCreateTime());
            dto.setLastUpdated(record.getLastUpdated());

            dtos.add(dto);
        }
        res.setList(dtos);

        return res;
    }

    @Override
    public AccountDTO getAccountById(int id) {
        Account record = this.getById(id);

        AccountDTO res = new AccountDTO();
        res.setId(record.getId());
        res.setEmail(record.getEmail());
        res.setName(record.getName());
        res.setUsername(record.getUsername());
        res.setPassword(record.getPassword());
        res.setAccountStatus(record.getAccountStatus().name());
        res.setCreateTime(record.getCreateTime());
        res.setLastUpdated(record.getLastUpdated());

        return res;
    }
}
