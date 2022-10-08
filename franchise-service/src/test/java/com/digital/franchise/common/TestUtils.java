package com.digital.franchise.common;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.request.UpdateOrderStatusRequest;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.enums.FranchiseStatus;
import com.digital.common.enums.OrderStatus;
import com.digital.franchise.entity.Franchise;
import com.digital.franchise.entity.Menu;
import com.digital.franchise.entity.MenuItem;
import com.digital.franchise.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Order getOrder() {
        return Order.builder()
                .customerId(1L)
                .franchise(getFranchise())
                .menuId(2L)
                .pickUpTime(LocalDateTime.parse("2022-10-08T01:08:42"))
                .status(OrderStatus.QUEUED)
                .build();
    }

    private static Franchise getFranchise() {
        return Franchise.builder()
                .name("Test")
                .status(FranchiseStatus.ACTIVE)
                .build();
    }

    public static OrderRequest getOrderRequest() {
        Map<Long, Integer> menuItemAndQuantityMap = new HashMap<>();
        menuItemAndQuantityMap.put(1l, 2);
        return OrderRequest.builder()
                .franchiseId(1l)
                .customerId(1l)
                .menuId(1l)
                .pickUpTime(LocalDateTime.now().plusMinutes(15))
                .menuItemAndQuantityMap(menuItemAndQuantityMap)
                .build();
    }

    public static List<MenuItem> getMenuItem() {
        return Arrays.asList(MenuItem.builder()
                .itemName("test item 1")
                .menu(getMenu())
                .price(BigDecimal.TEN)
                .quantity(1)
                .build());
    }

    private static Menu getMenu() {
        return Menu.builder()
                .franchise(getFranchise())
                .name("Menu 1")
                .build();
    }

    public static UpdateOrderStatusRequest updateOrderRequest() {
        return UpdateOrderStatusRequest.builder()
                .orderId(1l)
                .franchiseId(1l)
                .orderStatus(OrderStatus.COMPLETED)
                .build();
    }
}
