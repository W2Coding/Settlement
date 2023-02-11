package com.w2coding.settlementserver.member.domain.id;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerId implements Serializable {

	private Long store;

	private UUID member;

}
