package com.w2coding.settlementserver.util.snowflake;

import org.springframework.stereotype.Component;

@Component
public interface SequenceIdGenerator {

    long generateId();

} // https://github.com/gopalbala/distributed-idgen
