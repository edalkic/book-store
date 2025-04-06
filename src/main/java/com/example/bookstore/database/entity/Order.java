package com.example.bookstore.database.entity;

import com.example.bookstore.eventlistener.OrderCreatedEventListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
@NoArgsConstructor
@Getter
@EntityListeners({AuditingEntityListener.class, OrderCreatedEventListener.class})
@Slf4j
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Double totalAmount;

    @CreatedDate
    @Column(name = "creation_time", updatable = false)
    private Date creationTime;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Order(Double totalAmount, Set<OrderItem> orderItems, Customer customer) {
        this.totalAmount = totalAmount;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
