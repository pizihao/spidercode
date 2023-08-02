package com.example.druidtest.util;

public class IdWorker {

    private final long twepoch = 1483200000000L;
    private final long serviceID = 16L;
    private final long maxServiceID = 65535L;
    private final long sequenceBits = 7L;
    private final long workerIdShift = 7L;
    private final long timestampLeftShift = 23L;
    private final long sequenceMask = 127L;
    private final long serviceId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public IdWorker(long serviceId) {
        if (serviceId <= 65535L && serviceId >= 0L) {
            this.serviceId = serviceId;
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 65535L));
        }
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 127L;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - 1483200000000L << 23 | this.serviceId << 7 | this.sequence;
        }
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
