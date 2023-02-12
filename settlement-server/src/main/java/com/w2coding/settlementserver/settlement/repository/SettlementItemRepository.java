package com.w2coding.settlementserver.settlement.repository;

import com.w2coding.settlementserver.settlement.domain.SettlementItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettlementItemRepository<T extends SettlementItem> extends JpaRepository<T, Long> {

}
