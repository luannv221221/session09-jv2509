package com.ra.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseWrapper <T>{
    private int httpCode;
    private boolean success;
    private String message;
    private T data;
    private Object errors;
    private String timestamp;

    public static <T> ResponseWrapper<T> success(T data,String message,int httpCode){
        return ResponseWrapper.<T>builder()
                .httpCode(httpCode)
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

    public static <T> ResponseWrapper<T> error(T error,String message,int httpCode){
        return ResponseWrapper.<T>builder()
                .httpCode(httpCode)
                .success(false)
                .message(message)
                .errors(error)
                .timestamp(LocalDateTime.now().toString())
                .build();
    }

}
