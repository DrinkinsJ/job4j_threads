package ru.job4j.pools;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelIndexSearchTest {

    @Test
    public void whenSmallArrayFindIndex() {
        Integer[] array = {3, 4, 1};
        int res = ParallelIndexSearch.find(array, 4);
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void whenBigArrayFindIndex() {
        Integer[] array = new Integer[500];
        for (int i = 0; i < 500; i++) {
            array[i] = i;
        }
        int res = ParallelIndexSearch.find(array, 333);
        assertThat(res).isEqualTo(333);
    }

    @Test
    public void whenSmallArrayFindObj() {
        Object[] array = {"first", "second", "third"};
        int res = ParallelIndexSearch.find(array, "second");
        assertThat(res).isEqualTo(1);
    }

    @Test
    public void whenBigArrayFindObj() {
        Object[] array = new Object[500];
        for (int i = 0; i < 500; i++) {
            array[i] = String.format("Object #%s", i);
        }
        int res = ParallelIndexSearch.find(array, "Object #333");
        assertThat(res).isEqualTo(333);
    }

    @Test
    public void whenObjNotFound() {
        Object[] array = {"First", "Third", "Fourth"};
        int res = ParallelIndexSearch.find(array, "Cat");
        assertThat(res).isEqualTo(-1);
    }
}