package com.volvo.emspmicroservice.cardservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
public class RabbitMQConsumerTest {

    @Test
    public void testReceive() throws Exception {
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }
}
