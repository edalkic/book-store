package com.example.bookstore.service;

import com.example.bookstore.database.entity.Book;
import com.example.bookstore.database.repository.BookRepository;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookStockDto;
import com.example.bookstore.exception.EntityNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public Long createBook(BookDto bookDto) {

        Book book = new Book(bookDto.name, bookDto.stockQuantity, bookDto.amount);
        Book bookEntity = bookRepository.save(book);
        return bookEntity.getId();
    }

    public void updateStock(BookStockDto bookStockDto) throws EntityNotFound {

        Optional<Book> bookOptional = bookRepository.findById(bookStockDto.bookId);
        if (bookOptional.isEmpty())
            throw new EntityNotFound("Book not found, id: " + bookStockDto.bookId);

        Book book = bookOptional.get();
        book.setStockQuantity(bookStockDto.stockQuantity);
        bookRepository.save(book);
        //return HttpStatus.OK;
    }
}
