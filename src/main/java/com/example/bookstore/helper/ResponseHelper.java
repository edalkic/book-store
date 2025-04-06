package com.example.bookstore.helper;

import com.example.bookstore.dto.base.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHelper {


    public static <T> ResponseEntity<BaseResponse<T>> success(T data) {

        return new ResponseEntity<BaseResponse<T>>(
                new BaseResponse<T>(true, HttpStatus.OK.value(), "", data),
                HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> success(String message) {

        return new ResponseEntity<BaseResponse<T>>(
                new BaseResponse<T>(true, HttpStatus.OK.value(), message, null),
                HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> success(T data, String message) {

        return new ResponseEntity<BaseResponse<T>>(
                new BaseResponse<T>(true, HttpStatus.OK.value(), message, data),
                HttpStatus.OK);
    }

    public static <T> ResponseEntity<BaseResponse<T>> error(String message, HttpStatus httpStatus) {

        return new ResponseEntity<BaseResponse<T>>(
                new BaseResponse<T>(false, httpStatus.value(), message, null),
                HttpStatus.OK);
    }
}
