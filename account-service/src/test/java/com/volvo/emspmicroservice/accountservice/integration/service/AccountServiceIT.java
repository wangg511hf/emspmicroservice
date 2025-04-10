package com.volvo.emspmicroservice.accountservice.integration.service;

import com.volvo.emspmicroservice.accountservice.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import com.volvo.emspmicroservice.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class AccountServiceIT {

    @Autowired
    AccountService accountService;

    /**
     * Sort Account data by last_updated field in desc order, and pagination API test case
     */
    @Test
    public void testQueryAccountPage() {
        PageQuery query = new PageQuery();
        query.setPageNum(1);
        query.setPageSize(5);

        PageDTO<AccountDTO> testRes = accountService.queryAccountPage(query);
        assertEquals("After pagination, total record number is wrong!", 7L, testRes.getTotalNum());
        assertEquals("After pagination, total page number is wrong!", 2L, testRes.getTotalPage());
    }
}