package ru.guybydefault;

import ru.guybydefault.domain.MatrixExpression;

public class Main {

    public static void main(String[] args) {
        String filename = System.in.read();
        IO io = new IO();
        MatrixExpression me = new MatrixExpression(io.inCustomXml(filename));

    }
}
