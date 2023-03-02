package ru.job4j.io;

import java.io.*;

public class Analysis {

    boolean available = true;
    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(line -> {
                if (line.startsWith("500") || line.startsWith("400") && available) {
                    available = false;
                    builder.append(line.substring(4)).append(";");
                }
                if (line.startsWith("300") || line.startsWith("200") && !available) {
                    available = true;
                    builder.append(line.substring(4)).append(";").append(System.lineSeparator());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}