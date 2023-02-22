package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ReadFile implements FileReader {

    private final File file;

    public ReadFile(File file) {
        this.file = file;
    }

    public String content(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) > -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
