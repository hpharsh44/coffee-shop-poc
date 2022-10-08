package com.digital.common.dto.request;

import com.digital.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusRequest {
    @NotNull
    private Long franchiseId;
    @NotNull
    private Long orderId;
    @NotNull
    private OrderStatus orderStatus;
}
