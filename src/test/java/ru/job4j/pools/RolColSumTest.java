package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.pools.RolColSum.*;

public class RolColSumTest {

    private final int[][] matrix = {{1, 2},
                                    {3, 4}};
    private final Sums[] expected = {new Sums(3, 4),
                                     new Sums(7, 6)};


    @Test
    public void whenLinerMatrixRolColSums() {
        assertThat(sum(matrix)).isEqualTo(expected);
    }

    @Test
    public void whenAsyncMatrixRolColSums() throws ExecutionException, InterruptedException {
        assertThat(asyncSum(matrix)).isEqualTo(expected);
    }
}