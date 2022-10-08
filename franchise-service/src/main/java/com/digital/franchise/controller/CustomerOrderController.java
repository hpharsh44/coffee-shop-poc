package com.digital.franchise.controller;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.franchise.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/{customer_id}/orders")
    public ResponseEntity<PageListDTO<CustomerOrderDTO>> getAllCustomerOrder(@PathVariable("customer_id") Long customerId, Pageable pageable) {
        return new ResponseEntity(customerOrderService.getAllCustomerOrder(customerId, pageable), HttpStatus.OK);
    }

    @GetMapping("/{customer_id}/orders/{order_id}")
    public ResponseEntity<CustomerOrderDTO> getCustomerOrderByOrderId(@PathVariable("customer_id") Long customerId, @PathVariable("order_id") Long orderId) {
        return new ResponseEntity(customerOrderService.getCustomerOrderByOrderId(customerId, orderId), HttpStatus.OK);
    }


}
