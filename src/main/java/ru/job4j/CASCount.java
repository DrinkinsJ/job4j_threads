package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count;

    public CASCount(AtomicInteger count) {
        this.count = count;
    }

    public void increment() {
        int temp;
        int ref;
        do {
            temp = get();
            ref = temp + 1;
        } while (!count.compareAndSet(temp, ref));
    }

    public int get() {
        return count.get();
    }
}