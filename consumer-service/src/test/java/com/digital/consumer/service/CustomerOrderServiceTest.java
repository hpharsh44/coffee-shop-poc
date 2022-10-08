package com.digital.consumer.service;

import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.exception.NotFoundException;
import com.digital.consumer.client.FranchiseClient;
import com.digital.consumer.common.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerOrderServiceTest {

    @InjectMocks
    private CustomerOrderService customerOrderService;

    @Mock
    private FranchiseClient franchiseClient;

    @Test
    public void placeOrder_success() {
        when(franchiseClient.placeOrder(any())).thenReturn(TestUtils.getCustomerOrderDTO());
        CustomerOrderDTO response = customerOrderService.placeOrder(any());
        Assertions.assertNotNull(response);
    }

    @Test
    public void placeOrder_fail() {
        when(franchiseClient.placeOrder(any())).thenThrow(NotFoundException.class);
        Assertions.assertThrows(NotFoundException.class, () -> customerOrderService.placeOrder(any()));
    }

    @Test
    public void getCustomerOrderByOrderId_success() {
        when(franchiseClient.getCustomerOrderByOrderId(any(), any())).thenReturn(TestUtils.getCustomerOrderDTO());
        CustomerOrderDTO response = customerOrderService.getCustomerOrderByOrderId(any(), any());
        Assertions.assertNotNull(response);
    }

    @Test
    public void getAllCustomerOrder_success() {
        PageListDTO<CustomerOrderDTO> pageListDTO = PageListDTO.<CustomerOrderDTO>builder()
                .items(Arrays.asList(TestUtils.getCustomerOrderDTO()))
                .totalPages(1)
                .totalElements(1)
                .pageNumber(0)
                .pageSize(1)
                .build();
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"));

        when(franchiseClient.getAllCustomerOrder(1L, 0, 1)).thenReturn(pageListDTO);
        PageListDTO<CustomerOrderDTO> response = customerOrderService.getAllCustomerOrder(1L, pageable);
        Assertions.assertEquals(1, response.getTotalElements());
    }
}
