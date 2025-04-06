package com.example.bookstore.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "amount")
    private Double amount;

    @OneToMany(mappedBy = "book")
    private Set<OrderItem> orderItems;

    public Book(String name, int stockQuantity, Double amount) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.amount = amount;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
