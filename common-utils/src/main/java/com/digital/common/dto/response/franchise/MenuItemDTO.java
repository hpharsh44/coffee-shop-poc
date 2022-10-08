package com.digital.common.dto.response.franchise;

import com.digital.common.enums.OrderItemCategory;
import com.digital.common.enums.OrderItemSubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long id;

    private OrderItemCategory category;

    private OrderItemSubCategory subCategory;

    private String itemName;

    private BigDecimal price;

    private Integer quantity;
}
