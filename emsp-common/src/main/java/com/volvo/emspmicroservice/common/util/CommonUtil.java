package com.volvo.emspmicroservice.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CommonUtil {

    @Value("${custom.id-config.country-codes}")
    private String[] countryCodes;

    @Value("${custom.id-config.vendor-codes}")
    private String[] vendorCodes;

    public String generateRandomEMAID() {
        String COUNTRY_CODE = countryCodes[ThreadLocalRandom.current().nextInt(countryCodes.length)];
        String VENDOR_CODE = vendorCodes[ThreadLocalRandom.current().nextInt(vendorCodes.length)];
        Long randomNum = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);

        return String.format("%s-%s-%d", COUNTRY_CODE, VENDOR_CODE, randomNum);
    }
}
