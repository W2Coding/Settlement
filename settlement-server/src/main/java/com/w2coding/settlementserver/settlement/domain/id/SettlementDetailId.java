package com.w2coding.settlementserver.settlement.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementDetailId implements Serializable {

    private Long settlement;

    private Long settlementItem;

}
