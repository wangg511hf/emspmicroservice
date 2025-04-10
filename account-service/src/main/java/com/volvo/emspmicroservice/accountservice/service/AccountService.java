package com.volvo.emspmicroservice.accountservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.emspmicroservice.accountservice.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.accountservice.domain.Account;
import com.volvo.emspmicroservice.common.query.PageQuery;

public interface AccountService extends IService<Account> {

    public boolean isAccountExists(String email);

    public PageDTO<AccountDTO> queryAccountPage(PageQuery query);
}
