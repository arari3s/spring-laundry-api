package com.example.laundry.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponse {
    private String billId;
    private LocalDateTime transDate;
    private List<BillDetailResponse> billDetails;
}
