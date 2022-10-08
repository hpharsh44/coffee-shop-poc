package com.digital.franchise.entity;

import com.digital.common.dto.response.franchise.CustomerOrderDTO;
import com.digital.common.entity.Auditable;
import com.digital.common.enums.OrderStatus;
import com.digital.common.enums.OrderType;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Auditable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise")
    private Franchise franchise;

    private Long menuId;

    private Long customerId;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    private LocalDateTime pickUpTime;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public static CustomerOrderDTO build(Order order) {
        if (order == null) {
            return null;
        }
        return CustomerOrderDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .franchiseId(order.getFranchise().getId())
                .menuId(order.getMenuId())
                .totalAmount(order.getTotal())
                .orderStatus(order.getStatus())
                .orderItems(OrderItem.build(order.getOrderItems()))
                .pickUpTime(order.getPickUpTime())
                .orderType(order.getOrderType())
                .build();
    }

    public static List<CustomerOrderDTO> build(List<Order> orders) {
        if (CollectionUtils.isEmpty(orders) == false) {
            return orders.stream().map(order -> Order.build(order)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
