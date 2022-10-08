package com.digital.common.dto.response.franchise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderItemDTO {
    private Long id;

    private Long menuItemId;

    private BigDecimal price;

    private Integer quantity;
}
