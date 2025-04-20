package com.volvo.emspmicroservice.accountservice.infrastructure.repository.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.infrastructure.converter.AccountConverter;
import com.volvo.emspmicroservice.accountservice.infrastructure.repository.AccountRepository;
import com.volvo.emspmicroservice.accountservice.infrastructure.DO.AccountDO;
import com.volvo.emspmicroservice.accountservice.infrastructure.mapper.AccountMapper;
import com.volvo.emspmicroservice.common.query.PageQuery;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl extends ServiceImpl<AccountMapper, AccountDO> implements AccountRepository {

    private final AccountMapper accountMapper;
    private final AccountConverter accountConverter;

    @Override
    public boolean isAccountExists(String email) {
        return accountMapper.exists(new LambdaQueryWrapper<AccountDO>()
                .eq(AccountDO::getEmail, email));
    }

    public Account createAccount(Account account) {
        AccountDO accountDO = accountConverter.fromDomain(account);
        boolean isSuccess = this.save(accountDO);
        if(isSuccess) {
            Account savedAccount = this.getByEmail(accountDO.getEmail());
            return savedAccount;
        }

        throw new RuntimeException("Card created failed at CardRepository layer!");
    }

    public Account getByEmail(String email) {
        List<AccountDO> records = lambdaQuery()
                .eq(AccountDO::getEmail, email)
                .list();
        if (CollUtil.isNotEmpty(records) && records.size() == 1) {
            return accountConverter.toDomain(records.get(0));
        } else if(CollUtil.isEmpty(records)) {
            throw new RuntimeException("Account does not exist!");
        }

        throw new RuntimeException("Duplicate email address exists, please check the database!");
    }

    public Account changeAccountStatus(Account account) {
        AccountDO before = accountConverter.fromDomain(account);
        accountMapper.updateById(before);

        AccountDO after = this.getById(before.getId());
        Account ret = accountConverter.toDomain(after);

        return ret;
    }

    @Override
    public PageDTO<Account> queryAccountPage(PageQuery query) {
        Page<AccountDO> queryRes = lambdaQuery()
                .orderByDesc(AccountDO::getLastUpdated)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));

        PageDTO<Account> res = new PageDTO<>();
        res.setTotal_num(queryRes.getTotal());
        res.setTotalPage(queryRes.getPages());

        List<AccountDO> records = queryRes.getRecords();
        List<Account> list = CollUtil.newArrayList();
        for(AccountDO record : records) {
            Account acc = new Account(
                    record.getId(),
                    record.getEmail(),
                    record.getName(),
                    record.getUsername(),
                    record.getPassword(),
                    record.getAccountStatus(),
                    record.getCreateTime(),
                    record.getLastUpdated()
            );
            list.add(acc);
        }
        res.setList(list);

        return res;
    }

    @Override
    public Account getAccountById(Integer id) {
        AccountDO record = this.getById(id);

        return accountConverter.toDomain(record);
    }
}
