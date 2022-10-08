package com.digital.franchise.controller;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.request.UpdateOrderStatusRequest;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.franchise.service.FranchiseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/franchise")
public class FranchiseOrderController {

    @Autowired
    private FranchiseOrderService franchiseOrderService;

    @PostMapping("/order")
    public ResponseEntity<CustomerOrderDTO> placeOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity(franchiseOrderService.placeOrder(request), HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        return new ResponseEntity(franchiseOrderService.updateOrderStatus(request), HttpStatus.OK);
    }
}
