package com.digital.franchise.service;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.exception.BadRequestException;
import com.digital.franchise.common.TestUtils;
import com.digital.franchise.entity.Order;
import com.digital.franchise.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerOrderServiceTest {

    @InjectMocks
    private CustomerOrderService customerOrderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void getAllCustomerOrder_success() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"));
        when(orderRepository.findAllByCustomerId(1L, pageable)).thenReturn(new PageImpl<Order>(Arrays.asList(TestUtils.getOrder()), pageable, 1));
        PageListDTO<CustomerOrderDTO> response = customerOrderService.getAllCustomerOrder(1L, pageable);
        Assertions.assertNotNull(response.getItems());
    }

    @Test
    public void getAllCustomerOrder_return_empty() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"));
        when(orderRepository.findAllByCustomerId(1L, pageable)).thenReturn(new PageImpl<Order>(new ArrayList<>(), pageable, 0));
        PageListDTO<CustomerOrderDTO> response = customerOrderService.getAllCustomerOrder(1L, pageable);
        Assertions.assertEquals(0, response.getTotalElements());
    }

    @Test
    public void getCustomerOrderByOrderId_success() {

        when(orderRepository.findByCustomerIdAndId(1l, 1l)).thenReturn(TestUtils.getOrder());
        CustomerOrderDTO response = customerOrderService.getCustomerOrderByOrderId(1l, 1l);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1l, response.getCustomerId());
    }

    @Test
    public void getCustomerOrderByOrderId_throw_BadRequestException_for_customerId() {
        Assertions.assertThrows(BadRequestException.class, () -> customerOrderService.getCustomerOrderByOrderId(null, 1l));
    }

    @Test
    public void getCustomerOrderByOrderId_throw_BadRequestException_for_orderId() {
        Assertions.assertThrows(BadRequestException.class, () -> customerOrderService.getCustomerOrderByOrderId(1l, null));
    }

    @Test
    public void getCustomerOrderByOrderId_return_null() {

        when(orderRepository.findByCustomerIdAndId(1l, 1l)).thenReturn(null);
        CustomerOrderDTO response = customerOrderService.getCustomerOrderByOrderId(1l, 1l);
        Assertions.assertNull(response);
    }
}
