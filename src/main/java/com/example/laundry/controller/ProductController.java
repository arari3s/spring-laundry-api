package com.example.laundry.controller;

import com.example.laundry.dto.request.CreateProductRequest;
import com.example.laundry.dto.request.UpdateProductRequest;
import com.example.laundry.dto.response.CommonResponse;
import com.example.laundry.dto.response.ProductResponse;
import com.example.laundry.entity.Product;
import com.example.laundry.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody CreateProductRequest request) {
        ProductResponse productResponse = productService.createNew(request);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .status("success")
                .message("Product created")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Product product = productService.getById(id);
        CommonResponse<Product> response = CommonResponse.<Product>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Product found")
                .data(product)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        List<ProductResponse> productResponses = productService.getAll();
        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Product found")
                .data(productResponses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.update(request);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Product found")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productService.deleteById(id);
        CommonResponse<?> response = CommonResponse.builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("OK")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
