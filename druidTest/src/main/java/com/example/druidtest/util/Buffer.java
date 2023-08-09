package com.example.druidtest.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Buffer implements IBuffer<String> {

    long bufferSize;
    float flushThreshold;
    long timeout;

    /**
     * 记录信息，按字节存储
     */
    List<byte[]> byteList = new CopyOnWriteArrayList<>();

    long timestamp;

    volatile int state;

    /**
     * buffer占用的字节数
     */
    AtomicInteger size = new AtomicInteger(0);

    /**
     * 计数，当前正在修改的数量
     */
    AtomicInteger count = new AtomicInteger(0);

    /**
     * 标记，当前是否正在上传
     */
    AtomicBoolean sign = new AtomicBoolean(false);

    public Buffer(long bufferSize, float flushThreshold, long timeout) {
        this.bufferSize = bufferSize * 1024;
        this.flushThreshold = flushThreshold;
        this.timeout = timeout;
        this.timestamp = new Date().getTime();
        state = ACTIVE;
    }

    @Override
    public int state() {
        return state;
    }

    @Override
    @SuppressWarnings("java:S112")
    public boolean write(String r) {
        Objects.requireNonNull(r);
        try {
            if (size.get() < bufferSize && state == ACTIVE) {
                count.getAndIncrement();
                byte[] bytes = SerializeUtil.objectToByte(r);
                if (bytes.length == 0) {
                    return true;
                }
                // 更新size
                size.set(size.get() + bytes.length);
                byteList.add(bytes);
                count.getAndDecrement();
            } else {
                return false;
            }

            if (size.get() > bufferSize * flushThreshold && (sign.compareAndSet(false, true))) {
                // 修改状态为阻塞中
                state = BLOCK;
                upload();
                sign.set(false);
            }
        } catch (IOException e) {
            // 记录转化失败，丢弃
            e.printStackTrace();
        } finally {
            // 在最后更新时间戳
            this.timestamp = new Date().getTime();
            if (state() != CLOSEABLE) {
                // 修改完成设置状态为活跃
                state = ACTIVE;
            }
        }
        return true;
    }

    private synchronized void upload() throws IOException {
        if (byteList.isEmpty()) {
            return;
        }

        while (count.get() != 0) {
            // 等待
            LockSupport.parkNanos(5000);
        }

        try {
            String string = UUID.randomUUID().toString();
            log.info(" 发起上传 -{} 数据量 ： {}, 数据预估大小 ： {}", string, byteList.size(), size.get());
            for (byte[] b : byteList) {
                SerializeUtil.byteToObject(b);
            }
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2L));
            log.info("上传完成 -{} 数据量 ： {}, 数据预估大小 ： {}", string, byteList.size(), size.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteList.clear();
            size.set(0);
        }
    }

    @Override
    public void close() throws IOException {
        state = CLOSEABLE;
        upload();
    }

    @Override
    public long size() {
        return size.get();
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

}
