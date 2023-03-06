package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T o;

    public ParallelIndexSearch(T[] array, int from, int to, T o) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.o = o;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftSort = new ParallelIndexSearch<>(array, from, mid, o);
        ParallelIndexSearch<T> rightSort = new ParallelIndexSearch<>(array, mid + 1, to, o);
        leftSort.fork();
        rightSort.fork();
        Integer left = leftSort.join();
        Integer right = rightSort.join();
        return Math.max(left, right);
    }

    private int linearSearch() {
        int res = -1;
        for (int i = from; i <= to; i++) {
            if (o.equals(array[i])) {
                res = i;
                break;
            }
        }
        return res;
    }

    public static <T> int find(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, element));
    }
}
