package com.digital.franchise.service;

import com.digital.common.dto.request.OrderRequest;
import com.digital.common.dto.request.UpdateOrderStatusRequest;
import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.enums.OrderStatus;
import com.digital.common.enums.OrderType;
import com.digital.common.exception.BadRequestException;
import com.digital.common.exception.NotFoundException;
import com.digital.franchise.entity.Franchise;
import com.digital.franchise.entity.MenuItem;
import com.digital.franchise.entity.Order;
import com.digital.franchise.entity.OrderItem;
import com.digital.franchise.repository.FranchiseRepository;
import com.digital.franchise.repository.MenuItemRepository;
import com.digital.franchise.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FranchiseOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public CustomerOrderDTO placeOrder(OrderRequest request) {

        validateOrderType(request);

        Franchise franchise = franchiseRepository.findById(request.getFranchiseId()).
                orElseThrow(() -> new NotFoundException("Franchise not found by id: " + request.getFranchiseId()));
        log.info("Franchise found.");
        Map<Long, BigDecimal> menuItemMap = getFranchiseMenuItem(request.getMenuId());
        log.info("MenuItem found.");
        Order order = mapToFranchiseOrder(request, franchise, menuItemMap);
        log.info("checking for pending orders");
        List<Order> pendingOrder = orderRepository.findAllByStatusInOrderByUpdatedAtDesc(Arrays.asList(OrderStatus.PROCESSING, OrderStatus.QUEUED));
        orderRepository.save(order);
        log.info("Order is created");
        CustomerOrderDTO customerOrderDTO = Order.build(order);
        if (pendingOrder.isEmpty()) {
            log.info("No pending order is present");
            customerOrderDTO.setMessage("congratulations your order is InProgress...");
        } else {
            log.info("Pending order is present update message accordingly.");
            if (request.getOrderType() == OrderType.TAKE_AWAY)
                updateMessageIfOrderTypeIsTakeAway(request, pendingOrder, customerOrderDTO);
        }
        return customerOrderDTO;

    }

    private void updateMessageIfOrderTypeIsTakeAway(OrderRequest request, List<Order> pendingOrder, CustomerOrderDTO customerOrderDTO) {
        List<LocalDateTime> pendingOrderTime = pendingOrder.stream().map(or -> or.getUpdatedAt()).collect(Collectors.toList());
        Optional<LocalDateTime> minTime = pendingOrderTime.stream().min(LocalDateTime::compareTo);
        Optional<LocalDateTime> maxTime = pendingOrderTime.stream().min(LocalDateTime::compareTo);
        if (request.getPickUpTime().isAfter(maxTime.get().plusMinutes(10))) {
            log.info("Order is available on chosen time.");
            customerOrderDTO.setMessage("Your order is In Queue will move shortly in InProgress..." +
                    "You can pick your Order on your chosen time");
        } else if (request.getPickUpTime().equals(maxTime.get()) || request.getPickUpTime().equals(minTime.get())
                || request.getPickUpTime().isBefore(minTime.get())) {
            log.info("Order is not available on chosen time.");
            customerOrderDTO.setMessage("Your order is In Queue will move shortly in InProgress..." +
                    "Your Queue number is " + new Integer(pendingOrder.size() + 1));
        }
    }

    private void validateOrderType(OrderRequest request) {
        if (request.getOrderType() == OrderType.TAKE_AWAY && request.getPickUpTime() == null) {
            throw new BadRequestException("OrderType TAKE_AWAY must require PickUpTime.");
        }
    }

    private Map<Long, BigDecimal> getFranchiseMenuItem(Long menuId) {
        List<MenuItem> menuItems = menuItemRepository.findAllByMenuId(menuId);
        if (menuItems.isEmpty()) {
            throw new NotFoundException("No items founds by menuId: " + menuId);
        }
        Map<Long, BigDecimal> menuItemPriceMap = menuItems.stream().collect(Collectors.toMap(MenuItem::getId, MenuItem::getPrice));
        return menuItemPriceMap;
    }

    private Order mapToFranchiseOrder(OrderRequest customerOrder, Franchise franchise, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
        Order order = Order.builder()
                .franchise(franchise)
                .menuId(customerOrder.getMenuId())
                .customerId(customerOrder.getCustomerId())
                .status(OrderStatus.QUEUED)
                .orderType(customerOrder.getOrderType())
                .pickUpTime(customerOrder.getPickUpTime())
                .build();
        Set<OrderItem> orderItems = mapToOrderItems(customerOrder.getMenuItemAndQuantityMap(), order, menuItemPriceDetailsMap);
        order.setOrderItems(orderItems);

        BigDecimal total = orderItems.stream().map(orderItem -> orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
        order.setTotal(total);
        return order;
    }

    private Set<OrderItem> mapToOrderItems(Map<Long, Integer> selectedMenuItemAndQuantityMap, Order order, Map<Long, BigDecimal> menuItemPriceDetailsMap) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (Map.Entry<Long, Integer> menuItemAndQuantity : selectedMenuItemAndQuantityMap.entrySet()) {
            Long menuItemId = menuItemAndQuantity.getKey();
            Integer quantity = menuItemAndQuantity.getValue();

            OrderItem orderItem = OrderItem.builder()
                    .menuItemId(menuItemAndQuantity.getKey())
                    .quantity(quantity)
                    .orders(order)
                    .price(menuItemPriceDetailsMap.get(menuItemId) != null ? menuItemPriceDetailsMap.get(menuItemId) : BigDecimal.ZERO)
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    public String updateOrderStatus(UpdateOrderStatusRequest request) {
        Franchise franchise = franchiseRepository.findById(request.getFranchiseId()).
                orElseThrow(() -> new NotFoundException("Franchise not found by id: " + request.getFranchiseId()));
        log.info("Franchise found.");
        Order order = orderRepository.findByFranchiseAndId(franchise, request.getOrderId());
        if (order == null) {
            throw new NotFoundException("Order not found by franchiseId: " + request.getFranchiseId() + " orderId: " + request.getOrderId());
        }
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.FAILED ||
                order.getStatus() == OrderStatus.FAILED || order.getStatus() == OrderStatus.COMPLETED) {
            throw new BadRequestException("Order is not eligible for update.");
        }
        log.info("Order status is updated.");
        order.setStatus(request.getOrderStatus());
        orderRepository.save(order);
        return "Order status is updated to " + order.getStatus();
    }
}
