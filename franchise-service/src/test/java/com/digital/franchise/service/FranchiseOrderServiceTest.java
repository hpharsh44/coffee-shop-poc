package com.digital.franchise.service;

import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.exception.NotFoundException;
import com.digital.franchise.common.TestUtils;
import com.digital.franchise.repository.FranchiseRepository;
import com.digital.franchise.repository.MenuItemRepository;
import com.digital.franchise.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FranchiseOrderServiceTest {

    @InjectMocks
    private FranchiseOrderService franchiseOrderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private FranchiseRepository franchiseRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Test
    public void placeOrder_success() {
        when(franchiseRepository.findById(1l)).thenReturn(Optional.of(TestUtils.getOrder().getFranchise()));
        when(menuItemRepository.findAllByMenuId(1l)).thenReturn(TestUtils.getMenuItem());
        when(orderRepository.findAllByStatusInOrderByUpdatedAtDesc(anyList())).thenReturn(new ArrayList<>());

        CustomerOrderDTO response = franchiseOrderService.placeOrder(TestUtils.getOrderRequest());

        Assertions.assertNotNull(response);

    }

    @Test
    public void placeOrder_fail_franchise_not_found() {
        when(franchiseRepository.findById(1l)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> franchiseOrderService.placeOrder(TestUtils.getOrderRequest()));
    }

    @Test
    public void placeOrder_fail_menuItem_not_found() {
        when(franchiseRepository.findById(1l)).thenReturn(Optional.of(TestUtils.getOrder().getFranchise()));
        when(menuItemRepository.findAllByMenuId(1l)).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> franchiseOrderService.placeOrder(TestUtils.getOrderRequest()));

    }
    
}
