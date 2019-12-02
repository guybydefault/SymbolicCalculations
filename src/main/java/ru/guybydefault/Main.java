package ru.guybydefault;

import ru.guybydefault.domain.MatrixExpression;

import java.io.IOException;

public class Main {

    //in args put full path to files in order input file with custom xml and output file to write Math Ml
    public static void main(String[] args) throws IOException {

        if (args.length != 2) throw new IllegalArgumentException("Provide full path to files in order input file" +
                "with custom xml and output file to write Math Ml in args, please!");

        IO io = new IO();
        MatrixExpression me = new MatrixExpression(io.inCustomXml(args[0]));

        io.outMathMl(args[1], me.toString());
    }
}
