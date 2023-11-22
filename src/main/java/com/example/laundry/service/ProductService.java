package com.example.laundry.service;

import com.example.laundry.dto.request.CreateProductRequest;
import com.example.laundry.dto.request.UpdateProductRequest;
import com.example.laundry.dto.response.ProductResponse;
import com.example.laundry.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse createNew(CreateProductRequest request);

    Product getById(String id);

    List<ProductResponse> getAll();

    ProductResponse update(UpdateProductRequest request);

    void deleteById(String id);
}
