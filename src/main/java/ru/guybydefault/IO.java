package ru.guybydefault;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {
    public IO() {}

    public String inCustomXml(String filename) throws IOException {
        return Files.lines(Paths.get(filename), StandardCharsets.UTF_8).toString();
        //may be some xml special classes
    }

    public boolean outMathMl(String filename, String expr) throws IOException{

        Path path = Paths.get(filename);
        byte[] strToBytes = expr.getBytes();

        Files.write(path, strToBytes);

        String read = Files.readAllLines(path).get(0);
        return expr.equals(read);
    }
}
