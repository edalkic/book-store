package com.example.bookstore.exception;

public class StockNotEnoughException extends Exception {

    public StockNotEnoughException(String message) {
        super(message);
    }
}
