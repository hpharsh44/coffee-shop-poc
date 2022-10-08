package com.digital.consumer.service;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.consumer.client.FranchiseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerOrderService {

    @Autowired
    public FranchiseClient franchiseClient;

    public CustomerOrderDTO placeOrder(OrderRequest request) {
        log.info("Making call to create customer order.");
        return franchiseClient.placeOrder(request);
    }

    public PageListDTO<CustomerOrderDTO> getAllCustomerOrder(Long customerId, Pageable pageable) {
        log.info("Making call to get all customer orders.");
        return franchiseClient.getAllCustomerOrder(customerId, pageable.getPageNumber(), pageable.getPageSize());
    }

    public CustomerOrderDTO getCustomerOrderByOrderId(Long customerId, Long orderId) {
        log.info("Making call to get customer order..");
        return franchiseClient.getCustomerOrderByOrderId(customerId, orderId);
    }
}
