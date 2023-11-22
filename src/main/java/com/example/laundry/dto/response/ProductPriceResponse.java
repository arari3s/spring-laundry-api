package com.example.laundry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPriceResponse {
    private String productPriceId;
    private Integer price;
    private String productId;
}
