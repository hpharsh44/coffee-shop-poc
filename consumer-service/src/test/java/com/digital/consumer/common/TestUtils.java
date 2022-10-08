package com.digital.consumer.common;

import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.enums.AddressType;
import com.digital.common.enums.OrderStatus;
import com.digital.consumer.entity.Customer;

import java.time.LocalDateTime;

public class TestUtils {

    public static CustomerOrderDTO getCustomerOrderDTO() {
        return CustomerOrderDTO.builder()
                .id(1L)
                .customerId(1L)
                .franchiseId(1L)
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-10-08T01:08:42"))
                .orderStatus(OrderStatus.QUEUED)
                .build();
    }

    public static Customer getCustomer() {
        return Customer.builder()
                .addressLine("test add")
                .addressType(AddressType.HOME)
                .city("Mumbai")
                .country("India")
                .firstName("test f")
                .lastName("test l")
                .postcode("123456")
                .mobileNumber("1234567890")
                .build();
    }
}
