package com.w2coding.settlementserver.util.id;

import org.springframework.stereotype.Component;

@Component
public interface SequenceIdGenerator {

    long generateId();

} // https://github.com/gopalbala/distributed-idgen
