package com.w2coding.settlementserver.member.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.member.domain.StoreOwner;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, UUID> {

}
