package com.w2coding.settlementserver.util.snowflake;

import static com.w2coding.settlementserver.util.snowflake.Constants.NODE_ID_BIT_LEN;
import static com.w2coding.settlementserver.util.snowflake.Constants.SEQUENCE_BIT_LEN;

import java.time.Instant;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SnowflakeSequenceIdGenerator implements SequenceIdGenerator {

    private final Integer generatingNodeId = 31;

    private final int maxSequence = (int) Math.pow(2, SEQUENCE_BIT_LEN);
    private final int maxNodeVal = (int) Math.pow(2, NODE_ID_BIT_LEN);
    private final long EPOCH_START = Instant.EPOCH.toEpochMilli();


    private volatile long currentSequence = -1L;
    private final Object lock = new Object();
    private volatile long lastTimestamp = -1L;

    @PostConstruct
    public void checkNodeIdBounds() {
        if (generatingNodeId < 0 || generatingNodeId > maxNodeVal) {
            throw new IllegalArgumentException("Node id is < 0 or >" + maxNodeVal);
        }
    }

    @Override
    public long generateId() {
        checkNodeIdBounds();
        synchronized (lock) {
            long currentTimeStamp = getTimeStamp();
            if (currentTimeStamp < lastTimestamp) {
                throw new IllegalStateException("Clock moved backwards.");
            }
            if (currentTimeStamp == lastTimestamp) {
                currentSequence = currentSequence + 1 & maxSequence;
                if (currentSequence != 0) {
                    currentTimeStamp = waitNextMillis(currentTimeStamp);
                }
            }
            else {
                currentSequence = 0;
            }
            lastTimestamp = currentTimeStamp;
            long id = currentTimeStamp << (NODE_ID_BIT_LEN + SEQUENCE_BIT_LEN);
            id |= (generatingNodeId << SEQUENCE_BIT_LEN);
            id |= currentSequence;

            return id;
        }
    }

    private long getTimeStamp() {
        return Instant.now().toEpochMilli() - EPOCH_START;
    }

    private long waitNextMillis(long currentTimeStamp) {
        while (currentTimeStamp == lastTimestamp) {
            currentTimeStamp = getTimeStamp();
        }
        return currentTimeStamp;
    }

} // https://github.com/gopalbala/distributed-idgen
