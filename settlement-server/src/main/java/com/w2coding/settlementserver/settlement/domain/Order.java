package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Member;
import com.w2coding.settlementserver.settlement.domain.enums.OrderStatus;
import com.w2coding.settlementserver.settlement.domain.enums.OrderType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Order extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Settlement settlement;

    private Long orderDetailId; // we do not consider order detail

    private Long userId;        // we do not consider user

    private OrderStatus status;

    private OrderType type;
    
    // Payment, 지불과 양방향 연관관계
    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<>();

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

}
