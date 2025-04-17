package com.volvo.emspmicroservice.accountservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MessageServiceTest {

    @InjectMocks
    private IMessageService messageService;

    @Test
    public void testSend() {
        this.messageService.send("Hello World");
    }
}
