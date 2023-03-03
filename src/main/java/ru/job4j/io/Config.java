package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(line -> line.contains("="))
                    .map(line -> line.split("=", 2))
                    .forEach(
                            array -> {
                                if ("".equals(array[0]) || "".equals(array[1])) {
                                    throw new IllegalArgumentException();
                                }
                                values.put(array[0], array[1]);
                            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
             }
                return out.toString();
    }

    public static void main(String[] args) {
       System.out.println(new Config("data/app.properties"));
    }

}