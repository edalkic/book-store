package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderCreateDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.dto.base.BaseResponse;
import com.example.bookstore.exception.EntityNotFound;
import com.example.bookstore.exception.StockNotEnoughException;
import com.example.bookstore.helper.ResponseHelper;
import com.example.bookstore.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getById")
    public ResponseEntity<BaseResponse<OrderDto>> getOrderById(@RequestParam("id") @Min(value = 1, message = "id must be greater than or equal to 1") Long id) {

        try {
            OrderDto order = orderService.getOrderById(id);
            return ResponseHelper.success(order);
        } catch (EntityNotFound entityNotFound) {
            return ResponseHelper.error(entityNotFound.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while get order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Long>> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {

        try {
            Long orderId = orderService.createOrder(orderCreateDto);
            return ResponseHelper.success(orderId, "Order created successfully");
        } catch (EntityNotFound e) {
            return ResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (StockNotEnoughException e) {
            return ResponseHelper.error(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while creating order", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getByDateInterval")
    public ResponseEntity<BaseResponse<List<OrderDto>>> getOrdersByDateInterval(@RequestParam Long startTime, @RequestParam Long endTime) {

        try {
            List<OrderDto> ordersByDateInterval = orderService.getOrdersByDateInterval(startTime, endTime);
            return ResponseHelper.success(ordersByDateInterval);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while get orders by date interval", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
