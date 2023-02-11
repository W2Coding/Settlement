package com.w2coding.settlementserver.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.settlement.domain.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {

}
