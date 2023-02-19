package com.w2coding.settlementserver.settlement.domain;

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
public class PayoutHistory {

    @Id
    private Long id;

    @ManyToOne
    private Settlement settlement;

    private Boolean isPaid;

    private String remarks;

    private LocalDateTime createdAt;
}
