package com.wego.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse<T> implements Serializable {
    private String message;
    private T data;
    private int statusCode;

    public CommonResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}