package com.example.laundry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDetailResponse {
    private String billDetailId;
    private Integer weight;
    private String billId;
    private String productPriceId;
}
