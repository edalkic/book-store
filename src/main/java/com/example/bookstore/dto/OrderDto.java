package com.example.bookstore.dto;

import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    public Long id;
    public String createdDate;
    public Double amount;
    public String customerName;
    public List<OrderItemListDto> orderItemListDto;

    public static OrderDto fromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.id = order.getId();
        orderDto.amount = order.getTotalAmount();
        orderDto.createdDate = order.getCreationTime().toString();
        orderDto.customerName = order.getCustomer().getName();

        List<OrderItemListDto> orderItemDtoList = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemListDto orderItemDto = new OrderItemListDto();
            orderItemDto.bookName = orderItem.getBook().getName();
            orderItemDto.quantity = orderItem.getQuantity();
            orderItemDto.totalAmount = orderItem.getAmount();
            orderItemDtoList.add(orderItemDto);
        }

        orderDto.orderItemListDto = orderItemDtoList;
        return orderDto;
    }
}
