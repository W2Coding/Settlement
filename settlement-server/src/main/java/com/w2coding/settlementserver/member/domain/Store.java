package com.w2coding.settlementserver.member.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Store extends BaseTimeEntity {

    @Id
    private Long id;

    private String name;

    private Status status;

}
