package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 11; i++) {
                out.write("\n".getBytes());
                for (int j = 1; j < 11; j++) {
                    out.write((i * j + " ").getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}