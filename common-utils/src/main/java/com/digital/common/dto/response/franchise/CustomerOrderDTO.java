package com.digital.common.dto.response.franchise;

import com.digital.common.enums.OrderStatus;
import com.digital.common.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTO {
    private Long id;

    private Long customerId;

    private Long franchiseId;

    private Long menuId;

    private BigDecimal totalAmount;

    private List<CustomerOrderItemDTO> orderItems;

    private OrderStatus orderStatus;

    private OrderType orderType;

    private LocalDateTime pickUpTime;

    private String message;
}
