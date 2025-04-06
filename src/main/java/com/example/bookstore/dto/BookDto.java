package com.example.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

@ToString
public class BookDto {

    @NotNull(message = "Book name is null")
    @NotBlank(message = "Name is empty, enter book's name")
    public String name;

    public int stockQuantity;

    @NotNull(message = "Amount is null")
    public Double amount;


}
