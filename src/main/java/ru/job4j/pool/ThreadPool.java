package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < CORE_POOL_SIZE; i++) {
            run();
        }
        threads.forEach(Thread::start);
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private void run() {
        threads.add(new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }));
    }
}