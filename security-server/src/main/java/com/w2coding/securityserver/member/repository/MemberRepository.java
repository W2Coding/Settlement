package com.w2coding.securityserver.member.repository;

import java.util.Optional;
import java.util.UUID;

import com.w2coding.securityserver.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, UUID> {

	Boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
