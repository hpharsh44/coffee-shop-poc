package com.digital.common.dto.request;

import com.digital.common.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    private Long franchiseId;
    @NotNull
    private Long customerId;
    @NotNull
    private Long menuId;
    @NotNull
    private OrderType orderType;
    private LocalDateTime pickUpTime;
    private Map<Long, Integer> menuItemAndQuantityMap;
}
