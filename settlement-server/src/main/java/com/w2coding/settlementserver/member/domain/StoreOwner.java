package com.w2coding.settlementserver.member.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class StoreOwner extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;

    public void setMember(Member member) {
        this.member = member;
    }

}
