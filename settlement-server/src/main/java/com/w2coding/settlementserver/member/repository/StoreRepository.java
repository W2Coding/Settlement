package com.w2coding.settlementserver.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.member.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
