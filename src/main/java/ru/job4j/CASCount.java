package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(AtomicReference<Integer> count) {
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

    public static void main(String[] args) throws InterruptedException {
        AtomicReference<Integer> atomicReference = new AtomicReference<>();
        atomicReference.set(0);
        CASCount casCount = new CASCount(atomicReference);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 63; i++) {
                casCount.increment();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 65; i++) {
                casCount.increment();
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(casCount.get());
    }
}