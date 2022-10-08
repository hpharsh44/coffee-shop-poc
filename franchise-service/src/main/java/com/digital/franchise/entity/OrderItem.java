package com.digital.franchise.entity;

import com.digital.common.dto.response.franchise.CustomerOrderItemDTO;
import com.digital.common.entity.Auditable;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends Auditable {

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private Order orders;

    public static CustomerOrderItemDTO build(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        return CustomerOrderItemDTO.builder()
                .id(orderItem.getId())
                .menuItemId(orderItem.getMenuItemId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public static List<CustomerOrderItemDTO> build(Set<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItems) == false) {
            return orderItems.stream().map(orderItem -> OrderItem.build(orderItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
