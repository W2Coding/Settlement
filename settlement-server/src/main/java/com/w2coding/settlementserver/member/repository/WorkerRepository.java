package com.w2coding.settlementserver.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w2coding.settlementserver.member.domain.Worker;
import com.w2coding.settlementserver.member.domain.id.WorkerId;

public interface WorkerRepository extends JpaRepository<Worker, WorkerId> {

}
