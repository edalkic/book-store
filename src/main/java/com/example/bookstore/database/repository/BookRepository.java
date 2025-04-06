package com.example.bookstore.database.repository;

import com.example.bookstore.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    default void updateStock(Book book, int saleQuantity) {
        int currentQuantity = book.getStockQuantity();
        int newQuantity = currentQuantity - saleQuantity;
        book.setStockQuantity(newQuantity);
        Book save = this.save(book);
    }
}
