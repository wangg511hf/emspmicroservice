package com.volvo.emspmicroservice.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Result {

    private int code;
    private String message;

    public static Result fail(String message) {
        Result res = Result.of(400, message);
        return res;
    }
}
