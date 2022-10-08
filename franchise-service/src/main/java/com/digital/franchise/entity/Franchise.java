package com.digital.franchise.entity;

import com.digital.common.entity.Auditable;
import com.digital.common.enums.FranchiseStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "franchise")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Franchise extends Auditable {
    private String name;

    @Enumerated(EnumType.STRING)
    private FranchiseStatus status = FranchiseStatus.ACTIVE;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "franchise")
    @OrderBy("createdAt DESC")
    private Set<Menu> menus = new HashSet<Menu>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "franchise")
    @OrderBy("createdAt DESC")
    private Set<Order> orders = new HashSet<Order>();
}
