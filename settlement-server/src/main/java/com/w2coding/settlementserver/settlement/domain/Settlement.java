package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Store;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Settlement extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Store store;

    private Long totalOrderCost;

    private Long totalCompensationCost;

    private LocalDateTime paymentDate;

    private Integer approve;

    // TODO:: 참조 방법 알아보고 설정하기
//    // order, 주문과 양방향 연관관계
//    @OneToMany(mappedBy = "settlement")
//    private List<SettlementItem> settlementItems = new ArrayList<>();

    public void setStore(Store store) {
        this.store = store;
    }

}
