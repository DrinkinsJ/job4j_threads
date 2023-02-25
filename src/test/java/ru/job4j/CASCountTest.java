package ru.job4j;

import net.jcip.annotations.ThreadSafe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void when2ThreadsIncrements() throws InterruptedException {
        CASCount casCount = new CASCount(new AtomicInteger(0));
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                casCount.increment();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                casCount.increment();
            }
        });
        thread2.start();
        thread1.join();
        thread2.join();

        assertThat(casCount.get(), is(20000));
    }
}