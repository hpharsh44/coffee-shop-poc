package com.digital.franchise.entity;

import com.digital.common.dto.response.franchise.MenuItemDTO;
import com.digital.common.entity.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends Auditable {

    private String itemName;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu")
    @JsonIgnore
    private Menu menu;

    public static MenuItemDTO build(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .itemName(menuItem.getItemName())
                .price(menuItem.getPrice())
                .build();
    }

    public static List<MenuItemDTO> build(Set<MenuItem> menuItems) {
        if (CollectionUtils.isEmpty(menuItems) == false) {
            return menuItems.stream().map(menuItem -> MenuItem.build(menuItem)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
