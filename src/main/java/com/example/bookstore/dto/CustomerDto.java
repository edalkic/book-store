package com.example.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDto {

    @Email(message = "Customer email format is incorrect")
    public String email;

    @NotNull(message = "Customer name is null")
    @NotBlank(message = "Name is empty, enter customer's name")
    public String name;
}
