package ru.guybydefault.services;

import ru.guybydefault.domain.ExpressionInfo;
import ru.guybydefault.input.XMLParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IO {

    private final XMLParser xmlParser = new XMLParser();

    public ExpressionInfo inCustomXml(String filename) throws IOException {
        return xmlParser.parseExpression(Paths.get(filename).toFile());
    }

    public boolean outMathMl(String filename, String expr) throws IOException{

        Path path = Paths.get(filename);
        byte[] strToBytes = expr.getBytes();

        Files.write(path, strToBytes);

        String read = Files.readAllLines(path).get(0);
        return expr.equals(read);
    }
}
