package com.digital.common.dto.response.franchise;

import com.digital.common.enums.MenuStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
    private Long id;

    private String name;

    private String startTime;

    private String endTime;

    private MenuStatus status;

    private List<MenuItemDTO> menuItems;
}
