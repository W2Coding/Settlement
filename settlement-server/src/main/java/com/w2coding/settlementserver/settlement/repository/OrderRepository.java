package com.w2coding.settlementserver.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.settlement.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
