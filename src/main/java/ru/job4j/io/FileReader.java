package ru.job4j.io;

import java.util.function.Predicate;

public interface FileReader {
    String content(Predicate<Character> filter);
}