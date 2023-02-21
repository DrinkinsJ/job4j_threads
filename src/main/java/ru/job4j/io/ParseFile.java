package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;
    private final FileReader fileReader;
    private final FileSaver fileSaver;

    public ParseFile(File file, FileReader fileReader, FileSaver fileSaver) {
        this.file = file;
        this.fileReader = fileReader;
        this.fileSaver = fileSaver;
    }

    public String readContent() {
        return fileReader.content(data -> true);
    }

    public String readContentWithoutUnicode() {
        return fileReader.content(data -> data < 0x80);
    }

    public void saveContent(String content) {
        fileSaver.saveContent(content);
    }

}