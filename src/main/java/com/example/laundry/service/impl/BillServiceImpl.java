package com.example.laundry.service.impl;

import com.example.laundry.dto.request.BillDetailRequest;
import com.example.laundry.dto.request.BillRequest;
import com.example.laundry.dto.response.BillDetailResponse;
import com.example.laundry.dto.response.BillResponse;
import com.example.laundry.entity.Bill;
import com.example.laundry.entity.BillDetail;
import com.example.laundry.entity.ProductPrice;
import com.example.laundry.repository.BillRepository;
import com.example.laundry.service.BillDetailService;
import com.example.laundry.service.BillService;
import com.example.laundry.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillDetailService billDetailService;
    private final ProductPriceService productPriceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BillResponse createNew(BillRequest request) {
        Bill bill = Bill.builder()
                .transDate(LocalDateTime.now())
                .build();
        billRepository.saveAndFlush(bill);

        List<BillDetail> billDetails = new ArrayList<>();
        for (BillDetailRequest billDetailRequest : request.getBillDetails()) {
            ProductPrice productPrice = productPriceService.getById(billDetailRequest.getProductPriceId());
            BillDetail billDetail = BillDetail.builder()
                    .weight(billDetailRequest.getWeight())
                    .bill(bill)
                    .productPrice(productPrice)
                    .build();
            billDetails.add(billDetail);
        }

        billDetailService.createBulk(billDetails);
        bill.setBillDetails(billDetails);

        return mapToBillResponse(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public BillResponse getById(String id) {
        Bill bill = billRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "bill not found"));
        return mapToBillResponse(bill);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillResponse> getAll() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream().map(BillServiceImpl::mapToBillResponse).collect(Collectors.toList());
    }

    private static BillResponse mapToBillResponse(Bill bill) {
        List<BillDetailResponse> billDetailResponses = bill.getBillDetails().stream().map(billDetail -> BillDetailResponse.builder()
                .billDetailId(billDetail.getId())
                .weight(billDetail.getWeight())
                .billId(billDetail.getBill().getId())
                .productPriceId(billDetail.getProductPrice().getId())
                .build()
        ).collect(Collectors.toList());

        return BillResponse.builder()
                .billId(bill.getId())
                .transDate(bill.getTransDate())
                .billDetails(billDetailResponses)
                .build();
    }
}
