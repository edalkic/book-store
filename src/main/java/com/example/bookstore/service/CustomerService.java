package com.example.bookstore.service;

import com.example.bookstore.database.entity.Customer;
import com.example.bookstore.database.repository.CustomerRepository;
import com.example.bookstore.dto.CustomerDto;
import com.example.bookstore.dto.base.BaseListDto;
import com.example.bookstore.exception.EntityAlreadyExistException;
import com.example.bookstore.pagination.CustomerPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerPagination customerPagination;

    public Long createCustomer(CustomerDto customerDto) throws EntityAlreadyExistException {

        Optional<Customer> customerByEmail = customerRepository.findByEmail(customerDto.email);
        if (customerByEmail.isPresent())
            throw new EntityAlreadyExistException("Customer already existing, email: " + customerDto.email);

        Customer customer = new Customer(customerDto.email, customerDto.name);
        Customer customerEntity = customerRepository.save(customer);
        return customerEntity.getId();
    }

    public BaseListDto getCustomers(Pageable pageable) {
        Page<Customer> customerPage = customerPagination.getCustomers(pageable);

        List<Customer> customers = customerPage.get().collect(Collectors.toList());
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.email = customer.getEmail();
            customerDto.name = customer.getName();
            customerDtoList.add(customerDto);
        }
        BaseListDto baseListDto = BaseListDto.builder()
                .list(customerDtoList)
                .currentPageNumber(pageable.getPageNumber() + 1)
                .currentPageSize(pageable.getPageSize())
                .totalSize((int) customerPage.getTotalElements())
                .build();

        return baseListDto;
    }

}
