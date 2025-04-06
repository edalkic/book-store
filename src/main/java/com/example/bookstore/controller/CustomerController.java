package com.example.bookstore.controller;

import com.example.bookstore.dto.CustomerDto;
import com.example.bookstore.dto.base.BaseListDto;
import com.example.bookstore.dto.base.BaseResponse;
import com.example.bookstore.exception.EntityAlreadyExistException;
import com.example.bookstore.helper.ResponseHelper;
import com.example.bookstore.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Long>> createCustomer(@RequestBody @Valid CustomerDto customerDto) {

        try {
            Long customerId = customerService.createCustomer(customerDto);
            return ResponseHelper.success(customerId, "Customer saved successfully");
        } catch (EntityAlreadyExistException e) {
            return ResponseHelper.error(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while creating customer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse<BaseListDto>> getCustomers(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        PageRequest pageable = PageRequest.of(currentPage - 1, pageSize);

        try {
            BaseListDto customers = customerService.getCustomers(pageable);
            return ResponseHelper.success(customers);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while get customers", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
