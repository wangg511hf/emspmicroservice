package com.volvo.emspmicroservice.accountservice.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.infrastructure.DO.AccountDO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;

public interface AccountRepository extends IService<AccountDO> {

    boolean isAccountExists(String email);

    Account createAccount(Account account);

    Account getByEmail(String email);

    Account changeAccountStatus(Account account);

    PageDTO<Account> queryAccountPage(PageQuery query);

    Account getAccountById(Integer id);
}
