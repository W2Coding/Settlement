package com.w2coding.settlementserver.settlement.domain;

import com.w2coding.settlementserver.common.domain.BaseTimeEntity;
import com.w2coding.settlementserver.member.domain.Store;

import com.w2coding.settlementserver.settlement.domain.enums.SettlementStatus;
import jakarta.persistence.CascadeType;
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
	private Store store;

	private Long totalOrderCost;

	private Long totalCompensationCost;

	private LocalDateTime requestDate;

	private SettlementStatus status;

	@OneToMany(mappedBy = "settlement", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
	private List<SettlementDetail> settlementDetails = new ArrayList<>();

	@OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL)
	private List<PayoutHistory> settlementHistories = new ArrayList<>();

	public void setStore(Store store) {
		this.store = store;
	}

	public void setSettlementDetails(List<SettlementDetail> settlementDetails) {
		this.settlementDetails = settlementDetails;
	}
}
