package ru.guybydefault;

import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.visitors.OutputMathML;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    //in args put full path to files in order input file with custom xml and output file to write Math Ml
    public static void main(String[] args) throws IOException {

        if (args.length != 2) throw new IllegalArgumentException("Provide full path to files in order input file" +
                "with custom xml and output file to write Math Ml in args, please!");

        String inputFileName = args[0];
        String outputFileName = args[1];

        Symbol symbol = new Expression(ArithmeticFunctions.Plus, Arrays.asList(new Constant(1), new Constant(2))); // TODO parse from XML
        Context context = new Context();
        CalculationResult calculationResult = context.run(symbol);
        System.out.println(calculationResult.getSymbol().visit(new OutputMathML()));
        // calculationResult.getResult(); TODO transform to MathML

//        IO io = new IO();
//        ExpressionInfo me = io.inCustomXml(inputFileName);


//        io.outMathMl(outputFileName, me.toString());
    }
}
