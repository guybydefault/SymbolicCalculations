package ru.guybydefault;

import ru.guybydefault.domain.Symbol;

import java.io.IOException;

public class Main {

    //in args put full path to files in order input file with custom xml and output file to write Math Ml
    public static void main(String[] args) throws IOException {

        if (args.length != 2) throw new IllegalArgumentException("Provide full path to files in order input file" +
                "with custom xml and output file to write Math Ml in args, please!");

        String inputFileName = args[0];
        String outputFileName = args[1];

        Symbol symbol = null; // TODO parse from XML
        Context context = new Context();
        CalculationResult calculationResult = context.run(symbol);
        // calculationResult.getResult(); TODO transform to MathML

//        IO io = new IO();
//        ExpressionInfo me = io.inCustomXml(inputFileName);


//        io.outMathMl(outputFileName, me.toString());
    }
}
