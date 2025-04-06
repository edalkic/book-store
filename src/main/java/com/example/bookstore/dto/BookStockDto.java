package com.example.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import lombok.ToString;

@ToString
public class BookStockDto {

    @NotNull(message = "Book's id is null")
    public Long bookId;
    public int stockQuantity;
}
