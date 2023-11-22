package com.example.laundry.service;

import com.example.laundry.entity.BillDetail;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> createBulk(List<BillDetail> billDetails);
}
