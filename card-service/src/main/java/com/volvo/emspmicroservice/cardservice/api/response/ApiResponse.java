package com.volvo.emspmicroservice.cardservice.api.response;

import lombok.AllArgsConstructor;
import java.time.Instant;

@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T data;
    private String timestamp;

    // Success response static method
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data, Instant.now().toString());
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(200, "success", null, Instant.now().toString());
    }

    // error response static method
    public static ApiResponse<Void> error(int code, String message) {
        return new ApiResponse<>(code, message, null, Instant.now().toString());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
