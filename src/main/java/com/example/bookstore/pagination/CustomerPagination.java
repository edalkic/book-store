package com.example.bookstore.pagination;

import com.example.bookstore.database.entity.Customer;
import com.example.bookstore.database.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerPagination {

    private final CustomerRepository customerRepository;

    public Page<Customer> getCustomers(Pageable pageable) {
        List<Customer> allCustomers = customerRepository.findAll();
        List<Customer> paginationList = new ArrayList<>();
        int startItem = pageable.getPageSize() * pageable.getPageNumber();
        if (allCustomers.size() < startItem)
            paginationList = Collections.emptyList();
        else {
            int toIndex = Math.min(startItem + pageable.getPageSize(), allCustomers.size());
            paginationList = allCustomers.subList(startItem, toIndex);
        }

        Page<Customer> customersPage
                = new PageImpl<>(paginationList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), allCustomers.size());

        return customersPage;
    }
}
