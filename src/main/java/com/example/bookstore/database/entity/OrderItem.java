package com.example.bookstore.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem(int quantity, Book book, Double amount, Order order) {
        this.quantity = quantity;
        this.book = book;
        this.amount = amount;
        this.order = order;
    }

    public OrderItem(int quantity, Book book, Double amount) {
        this.quantity = quantity;
        this.book = book;
        this.amount = amount;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
