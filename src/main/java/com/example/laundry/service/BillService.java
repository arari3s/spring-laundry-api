package com.example.laundry.service;

import com.example.laundry.dto.request.BillRequest;
import com.example.laundry.dto.response.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse createNew(BillRequest request);
    BillResponse getById(String id);
    List<BillResponse> getAll();
}
