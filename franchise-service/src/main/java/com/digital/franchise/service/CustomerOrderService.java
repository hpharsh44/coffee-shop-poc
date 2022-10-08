package com.digital.franchise.service;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.exception.BadRequestException;
import com.digital.franchise.entity.Order;
import com.digital.franchise.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomerOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public PageListDTO<CustomerOrderDTO> getAllCustomerOrder(Long customerId, Pageable pageable) {
        if (customerId == null) {
            throw new BadRequestException("CustomerId is missing in input.");
        }
        Page<Order> orders = orderRepository.findAllByCustomerId(customerId, pageable);
        if (orders.isEmpty()) {
            return PageListDTO.<CustomerOrderDTO>builder()
                    .items(new ArrayList<>())
                    .pageNumber(orders.getNumber())
                    .pageSize(orders.getSize())
                    .totalElements(orders.getTotalElements())
                    .totalPages(orders.getTotalPages())
                    .build();
        }
        return PageListDTO.<CustomerOrderDTO>builder()
                .items(Order.build(orders.getContent()))
                .pageNumber(orders.getNumber())
                .pageSize(orders.getSize())
                .totalElements(orders.getTotalElements())
                .totalPages(orders.getTotalPages())
                .build();
    }

    public CustomerOrderDTO getCustomerOrderByOrderId(Long customerId, Long orderId) {
        if (customerId == null || orderId == null) {
            throw new BadRequestException("CustomerId or OrderId is missing in input.");
        }
        Order order = orderRepository.findByCustomerIdAndId(customerId, orderId);
        return Order.build(order);
    }
}
