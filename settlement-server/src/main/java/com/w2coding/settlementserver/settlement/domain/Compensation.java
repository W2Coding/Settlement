package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
public class Compensation extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Member member;

    // TODO:: 양방향 관계로 만들지 다음 회의(2.11)에서 결정
    @ManyToOne
    private Settlement settlement;

    private Boolean isReward;

    private Long cost;

    private String reason;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

}
