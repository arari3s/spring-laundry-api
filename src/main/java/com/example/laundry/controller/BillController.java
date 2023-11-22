package com.example.laundry.controller;

import com.example.laundry.dto.request.BillRequest;
import com.example.laundry.dto.response.BillResponse;
import com.example.laundry.dto.response.CommonResponse;
import com.example.laundry.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<?> createBill(@RequestBody BillRequest request) {
        BillResponse billResponse = billService.createNew(request);
        CommonResponse<BillResponse> response = CommonResponse.<BillResponse>builder()
                .code(HttpStatus.CREATED.value())
                .status("success")
                .message("Bill created")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBillById(@PathVariable String id) {
        BillResponse billResponse = billService.getById(id);
        CommonResponse<BillResponse> response = CommonResponse.<BillResponse>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Bill found")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllBill() {
        List<BillResponse> billResponses = billService.getAll();
        CommonResponse<List<BillResponse>> response = CommonResponse.<List<BillResponse>>builder()
                .code(HttpStatus.OK.value())
                .status("success")
                .message("Bill found")
                .data(billResponses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
