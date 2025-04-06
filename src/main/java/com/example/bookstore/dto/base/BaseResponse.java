package com.example.bookstore.dto.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseResponse<T> {

    public boolean isSuccess;
    public Integer code;
    public String message;
    public T data;

}
