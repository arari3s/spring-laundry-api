package com.example.laundry.controller;

import com.example.laundry.dto.request.CreateCustomerRequest;
import com.example.laundry.dto.request.UpdateCustomerRequest;
import com.example.laundry.dto.response.CommonResponse;
import com.example.laundry.dto.response.CustomerResponse;
import com.example.laundry.entity.Customer;
import com.example.laundry.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest request) {
        CustomerResponse customerResponse = customerService.createNew(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .code(HttpStatus.CREATED.value())
                .status("success")
                .message("Customer created")
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Customer found")
                .data(customer)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomer() {
        List<CustomerResponse> customerResponses = customerService.getAll();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Customer found")
                .data(customerResponses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse customerResponse = customerService.update(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Customer updated")
                .data(customerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Customer deleted")
                .data("OK")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
