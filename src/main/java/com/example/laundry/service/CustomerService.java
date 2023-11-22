package com.example.laundry.service;

import com.example.laundry.dto.request.CreateCustomerRequest;
import com.example.laundry.dto.request.UpdateCustomerRequest;
import com.example.laundry.dto.response.CustomerResponse;
import com.example.laundry.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse createNew(CreateCustomerRequest request);

    Customer getById(String id);

    List<CustomerResponse> getAll();

    CustomerResponse update(UpdateCustomerRequest request);

    void deleteById(String id);
}
