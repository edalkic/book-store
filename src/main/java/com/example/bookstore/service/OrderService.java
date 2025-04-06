package com.example.bookstore.service;

import com.example.bookstore.database.entity.Book;
import com.example.bookstore.database.entity.Customer;
import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.entity.OrderItem;
import com.example.bookstore.database.repository.BookRepository;
import com.example.bookstore.database.repository.CustomerRepository;
import com.example.bookstore.database.repository.OrderItemRepository;
import com.example.bookstore.database.repository.OrderRepository;
import com.example.bookstore.dto.OrderCreateDto;
import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.dto.OrderItemDto;
import com.example.bookstore.exception.EntityNotFound;
import com.example.bookstore.exception.StockNotEnoughException;
import com.example.bookstore.util.TimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDto getOrderById(Long id) throws EntityNotFound {

        Optional<Order> orderById = orderRepository.findById(id);
        if (!orderById.isPresent())
            throw new EntityNotFound("Entity not found with id " + id);

        Order order = orderById.get();
        OrderDto orderDto = OrderDto.fromOrder(order);
        return orderDto;
    }

    public Long createOrder(OrderCreateDto orderCreateDto) throws EntityNotFound, StockNotEnoughException {

        Optional<Customer> customerById = customerRepository.findById(orderCreateDto.customerId);
        if (customerById.isEmpty())
            throw new EntityNotFound("Customer not found with id " + orderCreateDto.customerId);

        Set<OrderItem> orderItems = new HashSet<>();
        Double totalAmount = 0D;
        for (OrderItemDto orderItemDto : orderCreateDto.orderItemDtoList) {

            Optional<Book> bookById = bookRepository.findById(orderItemDto.bookId);
            if (bookById.isEmpty())
                throw new EntityNotFound("Book not found with id " + orderItemDto.bookId);

            Book book = bookById.get();
            if (book.getStockQuantity() < orderItemDto.quantity)
                throw new StockNotEnoughException("Book stock not enough for sale");

            Double amount = orderItemDto.quantity * book.getAmount();
            totalAmount += amount;
            OrderItem orderItem = new OrderItem(orderItemDto.quantity, book, amount);
            orderItems.add(orderItem);
        }

        Customer customer = customerById.get();
        Order order = new Order(totalAmount, orderItems, customer);
        Order orderEntity = orderRepository.save(order);
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(orderEntity);
        });
        orderItemRepository.saveAll(orderItems);
        return orderEntity.getId();
    }

    public List<OrderDto> getOrdersByDateInterval(Long startTime, Long endTime) {

        Date startDate = TimeUtil.getDateFromEpochSecond(startTime);
        Date endDate = TimeUtil.getDateFromEpochSecond(endTime);

        List<Order> filteredOrderList = orderRepository.findByCreationTimeGreaterThanEqualAndCreationTimeLessThanEqual(startDate, endDate);
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : filteredOrderList) {
            OrderDto orderDto = OrderDto.fromOrder(order);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
