package com.digital.franchise.entity;

import com.digital.common.dto.response.franchise.MenuDTO;
import com.digital.common.entity.Auditable;
import com.digital.common.enums.MenuStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "menu")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends Auditable {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise")
    private Franchise franchise;

    @Enumerated(EnumType.STRING)
    private MenuStatus status;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MenuItem> menuItems = new HashSet<MenuItem>();

    public static MenuDTO build(Menu menu) {
        if (menu == null) {
            return null;
        }
        return MenuDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .status(menu.getStatus())
                .menuItems(MenuItem.build(menu.getMenuItems()))
                .build();
    }

    public static List<MenuDTO> build(Set<Menu> menus) {
        if (CollectionUtils.isEmpty(menus) == false) {
            return menus.stream().map(menu -> Menu.build(menu)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
