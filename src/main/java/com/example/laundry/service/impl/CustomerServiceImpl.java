package com.example.laundry.service.impl;

import com.example.laundry.dto.request.CreateCustomerRequest;
import com.example.laundry.dto.request.UpdateCustomerRequest;
import com.example.laundry.dto.response.CustomerResponse;
import com.example.laundry.entity.Customer;
import com.example.laundry.repository.CustomerRepository;
import com.example.laundry.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse createNew(CreateCustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .build();
        customerRepository.saveAndFlush(customer);
        return mapToCustomerResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAll() {
        return customerRepository.getAllCustomer().stream()
                .map(this::mapToCustomerResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = findByIdOrThrowNotFound(request.getId());
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customerRepository.saveAndFlush(customer);
        return mapToCustomerResponse(customer);
    }

    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    private Customer findByIdOrThrowNotFound(String id) {
        Optional<Customer> customer = customerRepository.getCustomerById(id);
        return customer.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    private CustomerResponse mapToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .build();
    }
}
