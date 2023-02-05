package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.settlement.domain.enums.PaymentType;
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
public class Payment extends BaseTimeEntity {

    @Id
    private Long id;

    @ManyToOne
    private Order order;

    private PaymentType paymentType;

    private Integer price;


    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
