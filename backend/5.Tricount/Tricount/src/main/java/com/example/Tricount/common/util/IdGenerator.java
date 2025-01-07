package com.example.Tricount.common.util;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

public class IdGenerator {
    private static final AtomicLong counter = new AtomicLong(1); // 초기값 1부터 시작

    // 고유한 Long 타입 ID 생성
    public static Long generateId() {
        return counter.getAndIncrement(); // 현재 값을 반환하고 증가
    }
}
