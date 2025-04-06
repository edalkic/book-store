package com.example.bookstore.database.repository;

import com.example.bookstore.database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreationTimeGreaterThanEqualAndCreationTimeLessThanEqual(Date startDate, Date endDate);
}
