package com.example.laundry.service;

import com.example.laundry.entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    List<ProductPrice> creatBulk(List<ProductPrice> productPrices);

    ProductPrice getById(String id);
}
