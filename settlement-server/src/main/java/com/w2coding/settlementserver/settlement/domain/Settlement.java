package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Settlement extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Member member;

    private Long totalOrderCost;

    private Long totalCompensationCost;

    private LocalDateTime paymentDate;

    private Integer approve;

    // order, 주문과 양방향 연관관계
    @OneToMany(mappedBy = "settlement")
    private List<Order> orders = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
    }

}
