package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelIndexSearchTest {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Test
    public void whenSmallArrayFindIndex() {
        Integer[] array = {3, 4, 1};
        Integer res = forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, 4));
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void whenBigArrayFindIndex() {
        Integer[] array = new Integer[500];
        for (int i = 0; i < 500; i++) {
            array[i] = i;
        }
        Integer res = forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, 333));
        assertThat(res).isEqualTo(333);
    }

    @Test
    public void whenSmallArrayFindObj() {
        Object[] array = {"first", "second", "third"};
        Object res = forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, "second"));
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void whenBigArrayFindObj() {
        Object[] array = new Object[500];
        for (int i = 0; i < 500; i++) {
            array[i] = String.format("Object #%s", i);
        }
        Object res = forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, "Object #333"));
        assertThat(res).isEqualTo(333);
    }

    @Test
    public void whenObjNotFound() {
        Object[] array = {"First", "Third", "Fourth"};
        Object res = forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, "Cat"));
        assertThat(res).isEqualTo(-1);
    }

}