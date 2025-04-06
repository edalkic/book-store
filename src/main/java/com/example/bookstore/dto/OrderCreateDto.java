package com.example.bookstore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateDto {

    @NotNull(message = "Customer id is null")
    public Long customerId;

    @Valid
    @NotEmpty(message = "At least one book must be purchased")
    public List<OrderItemDto> orderItemDtoList;
}
