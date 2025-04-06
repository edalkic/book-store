package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookStockDto;
import com.example.bookstore.dto.base.BaseResponse;
import com.example.bookstore.exception.EntityNotFound;
import com.example.bookstore.helper.ResponseHelper;
import com.example.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Long>> createBook(@RequestBody @Valid BookDto bookDto) {

        try {
            Long bookId = bookService.createBook(bookDto);
            return ResponseHelper.success(bookId, "Book saved successfully");
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while creating book record", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateStock")
    public ResponseEntity<BaseResponse<Void>> updateStock(@RequestBody @Valid BookStockDto bookStockDto) {
        try {
            bookService.updateStock(bookStockDto);
            return ResponseHelper.success("Book's stock updated successfully");

        } catch (EntityNotFound e) {
            return ResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseHelper.error("Error occurred while updating book stock", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
