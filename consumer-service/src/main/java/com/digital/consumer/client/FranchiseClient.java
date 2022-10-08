package com.digital.consumer.client;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.response.PageListDTO;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "franchise", url = "localhost:8081/api/v1")
public interface FranchiseClient {

    @PostMapping("/franchise/order")
    CustomerOrderDTO placeOrder(@RequestBody OrderRequest request);

    @GetMapping("/customer/{customer_id}/orders")
    PageListDTO<CustomerOrderDTO> getAllCustomerOrder(@PathVariable("customer_id") Long customerId, @RequestParam(name = "pageNumber", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "20") int size);

    @GetMapping("/customer/{customer_id}/orders/{order_id}")
    CustomerOrderDTO getCustomerOrderByOrderId(@PathVariable("customer_id") Long customerId, @PathVariable("order_id") Long orderId);
}
