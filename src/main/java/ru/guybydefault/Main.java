package ru.guybydefault;

import org.xml.sax.SAXException;
import ru.guybydefault.domain.Constant;
import ru.guybydefault.domain.Expression;
import ru.guybydefault.domain.Symbol;
import ru.guybydefault.dsl.functions.ArithmeticFunctions;
import ru.guybydefault.dsl.functions.ListFunctions;
import ru.guybydefault.dsl.functions.MatrixFunctions;
import ru.guybydefault.visitors.OutputMathMLVisitor;
import ru.guybydefault.io.xml.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        XMLParser parser = new XMLParser();
        Symbol symbol = parser.parse("src/main/resources/sampleAdd.xml");
//        String res = symbol.visit(OutputMathMLVisitor.getInstance()).toString();
//        System.out.println(OutputMathMLVisitor.getInstance().addMathMl(res));
//
//        Symbol symbol2 = new Expression(
//                ArithmeticFunctions.ListPlusList, Arrays.asList(
//                new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
//                new Expression(ListFunctions.List, new Constant(1), new Constant(1))));
//        res = symbol2.visit(OutputMathMLVisitor.getInstance()).toString();
//        System.out.println(OutputMathMLVisitor.getInstance().addMathMl(res));
//
//        Symbol symbol3 = new Expression(
//                MatrixFunctions.MatrixPlus, Arrays.asList(
//                new Expression(ListFunctions.List,
//                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
//                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
//                ),
//                new Expression(ListFunctions.List,
//                        new Expression(ListFunctions.List, new Constant(1), new Constant(1)),
//                        new Expression(ListFunctions.List, new Constant(1), new Constant(1))
//                )));
//        res = symbol3.visit(OutputMathMLVisitor.getInstance()).toString();
//        System.out.println(OutputMathMLVisitor.getInstance().addMathMl(res));

        Context context = new Context();
        CalculationResult calculationResult = context.run(symbol);
        Expression e = (Expression) calculationResult.getSymbol();
        String res = e.getArguments().get(e.getArguments().size() - 1).visit(OutputMathMLVisitor.getInstance()).toString();
        System.out.println(OutputMathMLVisitor.getInstance().addMathMl(res));
    }
}
