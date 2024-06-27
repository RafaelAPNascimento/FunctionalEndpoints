package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDto {

    private String message;
    private Integer result;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(Integer result) {
        this.result = result;
    }
}
