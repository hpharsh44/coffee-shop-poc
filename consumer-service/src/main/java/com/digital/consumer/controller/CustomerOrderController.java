package com.digital.consumer.controller;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.consumer.service.CustomerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/customer")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/order")
    public ResponseEntity<CustomerOrderDTO> placeOrder(@RequestBody OrderRequest request) {
        return new ResponseEntity(customerOrderService.placeOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/{customer_id}/orders")
    public ResponseEntity<PageListDTO<CustomerOrderDTO>> getAllCustomerOrder(@PathVariable("customer_id") Long customerId, Pageable pageable) {
        return new ResponseEntity(customerOrderService.getAllCustomerOrder(customerId, pageable), HttpStatus.OK);
    }

    @GetMapping("/{customer_id}/orders/{order_id}")
    public ResponseEntity<CustomerOrderDTO> getCustomerOrderByOrderId(@PathVariable("customer_id") Long customerId, @PathVariable("order_id") Long orderId) {
        return new ResponseEntity(customerOrderService.getCustomerOrderByOrderId(customerId, orderId), HttpStatus.OK);
    }
}
