package com.w2coding.settlementserver.member.service;

import org.springframework.stereotype.Service;

import com.w2coding.settlementserver.member.domain.Store;
import com.w2coding.settlementserver.member.exeption.StoreNotFoundException;
import com.w2coding.settlementserver.member.repository.StoreRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public Store findById(Long id) {
        return storeRepository.findById(id)
            .orElseThrow(StoreNotFoundException::new);
    }



}
