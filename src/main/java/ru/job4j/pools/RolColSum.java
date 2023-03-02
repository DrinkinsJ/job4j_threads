package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums traversal(int[][] matrix, int point) {
        int rowSum = 0;
        int colSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            rowSum += matrix[point][i];
            colSum += matrix[i][point];
        }
        return new Sums(rowSum, colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = traversal(matrix, i);
        }
        return sums;
    }

    private static CompletableFuture<Sums> sumsCompletableFuture(int[][] matrix, int point) {
        return CompletableFuture.supplyAsync(() -> traversal(matrix, point));
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = sumsCompletableFuture(matrix, i).get();
        }
        return sums;
    }
}