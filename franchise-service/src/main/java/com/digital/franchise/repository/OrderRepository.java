package com.digital.franchise.repository;

import com.digital.common.enums.OrderStatus;
import com.digital.franchise.entity.Franchise;
import com.digital.franchise.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByCustomerIdAndId(Long customerId, Long orderId);

    Order findByFranchiseAndId(Franchise franchise, Long orderId);

    Page<Order> findAllByCustomerId(Long customerId, Pageable pageable);

    List<Order> findAllByStatusInOrderByUpdatedAtDesc(List<OrderStatus> statuses);
}
