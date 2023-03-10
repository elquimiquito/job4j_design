package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Arrays.stream(args).sequential()
                .map(line -> {
                    if (line.charAt(0) != '-') {
                        throw new IllegalArgumentException("Error: This argument '"
                                                            + line
                                                            + "' does not start with a '-' character");
                    }
                    if (!line.contains("=")) {
                        throw new IllegalArgumentException("Error: This argument '"
                                                            + line
                                                            + "' does not contain an equal sign");
                    }
                    if (line.charAt(1) == '=') {
                        throw new IllegalArgumentException("Error: This argument '"
                                                            + line
                                                            + "' does not contain a key");
                    }
                    if ("".equals(line.substring(line.indexOf("=") + 1))) {
                        throw new IllegalArgumentException("Error: This argument '"
                                                            + line
                                                            + "' does not contain a value");
                    }
                    return line.split("=", 2);
                })
                .forEach(array -> {
                            if (array.length != 2) {
                                throw new IllegalArgumentException("Arguments not passed to program");
                            }
                            values.put(array[0].substring(1), array[1]);
                        });
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}