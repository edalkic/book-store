package com.example.bookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    @NotNull(message = "Book's id is null")
    public Long bookId;

    @Min(value = 1, message = "Quantity should be equal or greater than 1")
    public int quantity;
}
