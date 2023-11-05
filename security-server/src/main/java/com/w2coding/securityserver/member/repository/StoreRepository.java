package com.w2coding.securityserver.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.securityserver.member.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
