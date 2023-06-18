package com.w2coding.settlementserver.member.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, UUID> {

	Boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
