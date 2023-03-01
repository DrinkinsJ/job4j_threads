package ru.job4j.pools;

public class LinearIndexSearch<T> {

    public int linearSearch(Object[] array, Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
}
