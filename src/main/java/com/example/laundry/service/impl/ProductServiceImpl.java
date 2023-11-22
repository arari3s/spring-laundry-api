package com.example.laundry.service.impl;

import com.example.laundry.dto.request.CreateProductRequest;
import com.example.laundry.dto.request.ProductPriceRequest;
import com.example.laundry.dto.request.UpdateProductRequest;
import com.example.laundry.dto.response.ProductPriceResponse;
import com.example.laundry.dto.response.ProductResponse;
import com.example.laundry.entity.Product;
import com.example.laundry.entity.ProductPrice;
import com.example.laundry.repository.ProductRepository;
import com.example.laundry.service.ProductPriceService;
import com.example.laundry.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse createNew(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .duration(request.getDuration())
                .build();
        productRepository.saveAndFlush(product);

        List<ProductPrice> productPrices = new ArrayList<>();

        for (ProductPriceRequest productPriceRequest : request.getProductPrices()) {
            ProductPrice productPrice = ProductPrice.builder()
                    .price(productPriceRequest.getPrice())
                    .product(product)
                    .build();
            productPrices.add(productPrice);
        }
        productPriceService.creatBulk(productPrices);
        product.setProductPrices(productPrices);

        return mapToProductResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return productRepository.getAllProduct().stream()
                .map(this::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProductResponse update(UpdateProductRequest request) {
        Product product = findByIdOrThrowNotFound(request.getId());
        product.setName(request.getName());
        product.setDuration(request.getDuration());
        productRepository.saveAndFlush(product);
        return mapToProductResponse(product);
    }

    @Override
    public void deleteById(String id) {
        Product product = findByIdOrThrowNotFound(id);
        productRepository.delete(product);
    }

    private Product findByIdOrThrowNotFound(String id) {
        Optional<Product> product = productRepository.getProductById(id);
        return product.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
    }

    private ProductResponse mapToProductResponse(Product product) {
        List<ProductPriceResponse> productPriceResponses = product.getProductPrices().stream()
                .map(productPrice ->
                        ProductPriceResponse.builder()
                                .productPriceId(productPrice.getId())
                                .price(productPrice.getPrice())
                                .productId(productPrice.getProduct().getId())
                                .build()
                ).collect(Collectors.toList());
        return ProductResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .duration(product.getDuration())
                .productPrices(productPriceResponses)
                .build();
    }
}
