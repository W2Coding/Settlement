package com.w2coding.securityserver.member.service;

import org.springframework.stereotype.Service;

import com.w2coding.securityserver.member.domain.Store;
import com.w2coding.securityserver.member.exeption.StoreNotFoundException;
import com.w2coding.securityserver.member.repository.StoreRepository;

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
