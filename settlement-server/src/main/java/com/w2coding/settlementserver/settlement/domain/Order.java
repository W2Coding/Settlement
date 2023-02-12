package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.settlement.domain.enums.OrderStatus;
import com.w2coding.settlementserver.settlement.domain.enums.OrderType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("O")
@Entity
public class Order extends SettlementItem {

    private Long orderDetailId; // we do not consider order detail

    private Long userId;        // we do not consider user

    private OrderStatus status;

    private OrderType type;
    
    // Payment, 지불과 양방향 연관관계
    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<>();

}
