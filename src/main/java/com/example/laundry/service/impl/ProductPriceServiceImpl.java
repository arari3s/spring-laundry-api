package com.example.laundry.service.impl;

import com.example.laundry.entity.ProductPrice;
import com.example.laundry.repository.ProductPriceRepository;
import com.example.laundry.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    @Override
    public List<ProductPrice> creatBulk(List<ProductPrice> productPrices) {
        return productPriceRepository.saveAllAndFlush(productPrices);
    }

    @Override
    public ProductPrice getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    private ProductPrice findByIdOrThrowNotFound(String id) {
        return productPriceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product price not found"));
    }
}
