package com.example.bookstore.eventlistener;

import com.example.bookstore.database.entity.Order;
import com.example.bookstore.database.entity.OrderItem;
import com.example.bookstore.database.repository.BookRepository;
import com.example.bookstore.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;

@Component
@Slf4j
public class OrderCreatedEventListener {

    @PostPersist
    public void updateBookStock(final Object entity) {
        BookRepository bookRepository = BeanUtil.getBean(BookRepository.class);
        Order orderEntity = (Order) entity;
        for (OrderItem orderItem : orderEntity.getOrderItems()) {
            bookRepository.updateStock(orderItem.getBook(), orderItem.getQuantity());
            log.info("Book stock updated id {}", orderItem.getBook().getId());
        }
        log.info("Books stock update completed.");
    }
}
