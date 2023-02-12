package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Store;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Entity
public class SettlementItem extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Store store;

    private Long cost;

    public void setStore(Store store) {
        this.store = store;
    }
}
