package com.volvo.emspmicroservice.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyTimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // Auto create create_time when insert, last_updated is current time
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("lastUpdated", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // insert last_updated when updating
        this.setFieldValByName("lastUpdated", new Date(), metaObject);
    }
}
