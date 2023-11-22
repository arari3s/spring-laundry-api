package com.example.laundry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String productId;
    private String name;
    private Integer duration;
    private List<ProductPriceResponse> productPrices;
}
