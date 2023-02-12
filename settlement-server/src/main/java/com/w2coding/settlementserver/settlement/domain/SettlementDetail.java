package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.settlement.domain.enums.SettlementStatus;
import com.w2coding.settlementserver.settlement.domain.id.SettlementDetailId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(SettlementDetailId.class)
@Entity
public class SettlementDetail extends BaseTimeEntity {

    @Id
    @ManyToOne
    private Settlement settlement;

    @Id
    @ManyToOne
    private SettlementItem settlementItem;

    private SettlementStatus status;

}
